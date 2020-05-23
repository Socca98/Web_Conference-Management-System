import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';


@Component({
  selector: 'app-submission',
  templateUrl: './tab-submissions.component.html',
  styleUrls: ['./tab-submissions.component.css'],
  providers: [
    SubmissionsService,
  ]
})

export class TabSubmissionsComponent implements OnInit {
  submissions: Submission[];

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;
    });
  }
}
