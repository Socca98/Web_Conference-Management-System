import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';


@Component({
  selector: 'app-submission',
  templateUrl: './submissions.component.html',
  styleUrls: ['./submissions.component.css'],
  providers: [
    SubmissionsService,
  ]
})

export class SubmissionsComponent implements OnInit {
  proposal: Submission = {
    title: null,
    abstract: null,
    name: null,
    topics: null,
    listOfAuthors: null,
    metaInformation: null,
    fullPaper: null
  };
  submissions: Submission[] = [this.proposal, this.proposal, this.proposal];

  constructor(
    private submissionsService: SubmissionsService,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions().subscribe((result: Submission[]) => {
      this.submissions = result;
    });
  }
}
