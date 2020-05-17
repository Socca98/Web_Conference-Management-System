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

    // If it comes from a conference tab, we remove the id,
    // so the conference tabs no longer show on toolbar
    if (this.authService.isOnConferenceSite()) {
      localStorage.removeItem('conferenceId');
    }
  }

  /**
   * Retrieve user role for specific conference into LocalStorage
   */
  goToConferenceSite() {
    //check null

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
          user: string;
          affiliation: string;
          role: string;
          chair: boolean;
        };
        responseData = (response as any);

        localStorage.setItem('user', responseData.user);
        localStorage.setItem('role', responseData.role);
        localStorage.setItem('conferenceId', selectedConferenceId);

        // Open tab-details component
        this.router.navigate(['/conference/details']);
      },
      error: err => {
        alert('Error! Could not retrieve user information!' + err);
      }
    });
  }
}
