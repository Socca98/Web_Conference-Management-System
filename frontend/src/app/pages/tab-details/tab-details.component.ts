import {Component, Input, OnInit} from '@angular/core';
import {Conference} from '../../shared/models/conference';
import {AuthService} from '../../login/auth.service';
import {ConferencesService} from '../../shared/services/conferences.service';
import {Role} from '../../shared/models/role';


@Component({
  selector: 'app-tab-details',
  templateUrl: './tab-details.component.html',
  styleUrls: ['./tab-details.component.css'],
})
export class TabDetailsComponent implements OnInit {
  conference: Conference = {} as Conference;

  constructor(
    public conferencesService: ConferencesService,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.conferencesService.getConference(this.authService.conference.id).subscribe({
      next: (response: Conference) => {
        this.conference = response;
      },
      error: err => {
        alert(err + ' error!');
      }
    });
  }

  canEditDeadlines(): boolean {
    const userRole = this.authService.getUserRole();
    return userRole === Role.Chair || userRole === Role.CoChair;
  }
}
