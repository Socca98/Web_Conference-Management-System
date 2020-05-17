import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';


@Component({
  selector: 'app-submission',
  templateUrl: './tab-submissions.component.html',
  styleUrls: ['./tab-submissions.component.css'],
  providers: [
    SubmissionsService,
  ]
})

export class TabSubmissionsComponent implements OnInit {
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
