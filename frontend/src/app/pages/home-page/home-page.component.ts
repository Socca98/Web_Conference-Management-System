import {Component, OnInit} from '@angular/core';
import {Conference} from '../../shared/models/conference';
import {ConferencesService} from '../../shared/services/conferences.service';
import {AuthService} from '../../login/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {User} from '../../shared/models/user';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {RegisterDialogComponent} from '../../login/register/register-dialog.component';
import {DataService} from '../../shared/services/data.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [
    ConferencesService,
    DataService,
  ],
})
export class HomePageComponent implements OnInit {
  conferences: Conference[] = [];
  selectedTabIndex = 0;

  constructor(
    private authService: AuthService,
    private dataService: DataService,
    private conferencesService: ConferencesService,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private matDialog: MatDialog,
  ) {
    this.route.params.subscribe(params => {
      // We got here by invitation link, so we open register dialog and pass data
      if (params.linkId) {
        if (!this.authService.isLogged()) {
          const dialogConfig = new MatDialogConfig();
          dialogConfig.data = {
            linkId: params.linkId,
          };
          this.matDialog.open(RegisterDialogComponent, dialogConfig);
        } else {
          // If already logged in then abort
          this.snackBar.open('You must logout first!', '', {
            duration: 2000,
            panelClass: ['warning'],
          });
          return;
        }
      }
    });
  }

  ngOnInit(): void {
    this.conferencesService.getConferences().subscribe((result: Conference[]) => {
      this.conferences = result;
    });
  }

  /**
   * Retrieve user role for specific conference into LocalStorage
   */
  goToConferenceSite() {
    // Check if conference is selected from the list
    if (this.selectedTabIndex === undefined || !this.conferences.length) {
      this.snackBar.open('No conference selected!', 'OK', {
        duration: 1000
      });
      return;
    }

    const selectedConferenceId = this.conferences[this.selectedTabIndex].id;
    if (!this.authService.isLogged()) {
      this.snackBar.open('Please sign in!', 'OK', {
        duration: 1000,
        panelClass: ['warning'],
      });
      return;
    }

    this.authService.getUserInformation(selectedConferenceId).subscribe({
      next: (response) => {
        // custom response json, if you dont want to make an interface again
        let responseData: {
          username: string;
          email: string;
          affiliation: string;
          role: string;
          chair: boolean;
        };
        responseData = (response as any);

        // Save shared data in Local Storage, used on multiple components (retrieved from AuthService)
        this.authService.conference = this.conferences[this.selectedTabIndex];
        this.authService.user = {
          username: responseData.username,
          email: responseData.email,
          affiliation: responseData.affiliation,
          role: responseData.role,
          isChair: responseData.chair,
          payedAttend: false,
        } as User;

        // Best method for shared data between components is a SharedDataService
        this.dataService.setConference(this.conferences[this.selectedTabIndex]);


        // Open tab-details component
        this.router.navigate(['/conference/details']);
      },
      error: _ => {
        alert('Error! Login token expired!');
      }
    });
  }
}


