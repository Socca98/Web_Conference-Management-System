import {Component, OnInit} from '@angular/core';
import {Conference} from '../../interfaces/conference';
import {ConferencesService} from '../../services/conferences.service';

@Component({
  selector: 'app-tab-details',
  templateUrl: './tab-details.component.html',
  styleUrls: ['./tab-details.component.css'],
  providers: [ConferencesService],
})

export class TabDetailsComponent implements OnInit {
  conference: Conference = {startDate: null, endDate: null, submissionDeadline: null, abstractSubmissionDeadline: null};

  constructor(private conferenceService: ConferencesService) {
  }

  ngOnInit(): void {
    this.conferenceService.getConference('1').subscribe((response: Conference) => this.conference = response);
  }

}
