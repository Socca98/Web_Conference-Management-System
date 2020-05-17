import {Component, OnInit} from '@angular/core';
import {Conference} from '../../shared/models/conference';
import {ConferencesService} from '../../shared/services/conferences.service';
import {AuthService} from '../../login/auth.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [
    ConferencesService,
  ],
})
export class HomePageComponent implements OnInit {
  conferences: Conference[] = [];
  selectedTabIndex: number;

  constructor(
    private authService: AuthService,
    private conferencesService: ConferencesService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {

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
    if (this.selectedTabIndex === null) {
      this.snackBar.open('No conference selected!', 'OK', {
        duration: 1000
      });
      return;
    }

    const selectedConferenceId = this.conferences[this.selectedTabIndex].id;
    if (!this.authService.isLogged()) {
      this.snackBar.open('Please sign in!', 'OK', {
        duration: 1000
      });
      return;
    }

    this.authService.getUserInformation(selectedConferenceId).subscribe({
      next: (response) => {
        // custom response json, if you dont want to make an interface again
        let responseData: {
          username: string;
          affiliation: string;
          role: string;
          chair: boolean;
        };
        responseData = (response as any);

        localStorage.setItem('user', responseData.username);
        localStorage.setItem('conferenceId', selectedConferenceId);
        responseData.chair === true ? localStorage.setItem('role', 'Chair') : localStorage.setItem('role', responseData.role);

        // Open tab-details component
        this.router.navigate(['/conference/details']);
      },
      error: err => {
        alert('Error! Could not retrieve user information!' + err);
      }
    });
  }
}
