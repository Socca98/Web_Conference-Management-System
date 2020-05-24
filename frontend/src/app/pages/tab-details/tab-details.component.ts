import {Component, Input, OnInit} from '@angular/core';
import {Conference} from '../../shared/models/conference';
import {AuthService} from '../../login/auth.service';
import {ConferencesService} from '../../shared/services/conferences.service';


@Component({
  selector: 'app-tab-details',
  templateUrl: './tab-details.component.html',
  styleUrls: ['./tab-details.component.css'],
})
export class TabDetailsComponent implements OnInit {
  conference: Conference;

  constructor(
    public conferencesService: ConferencesService,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.conferencesService.getConference(this.authService.conference.id).subscribe({
      next: (response) => {
        alert(response);
      },
      error: err => {
        alert(err + 'response');
      }
    });
  }

}
