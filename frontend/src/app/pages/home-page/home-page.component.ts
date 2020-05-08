import {Component, OnInit} from '@angular/core';
import {Conference} from '../../shared/models/conference';
import {ConferencesService} from '../../shared/services/conferences.service';
import {AuthService} from '../../login/auth.service';
import {Router} from '@angular/router';

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
  ) {

  }

  ngOnInit(): void {
    this.conferencesService.getConferences().subscribe((result: Conference[]) => {
      this.conferences = result;
    });
  }

  goToConferenceSite() {
    // Retrieve user data into LocalStorage
    this.authService.getUserInformation(this.conferences[this.selectedTabIndex].id).subscribe({
      next: (response) => {
        let responseData: { user: string; role: string };
        responseData = (response as any);
        localStorage.setItem('user', responseData.user);
        localStorage.setItem('role', responseData.role);

        // Open tab-details component
        this.router.navigate(['/conference/details']);
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Could not retrieve user information!');
      }
    });


  }
}
