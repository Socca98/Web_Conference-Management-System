import { Component, OnInit } from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';

@Component({
  selector: 'app-tab-evaluations',
  templateUrl: './tab-evaluations.component.html',
  styleUrls: ['./tab-evaluations.component.css']
})
export class TabEvaluationsComponent implements OnInit {
  /**
   * This tab is for Chair/Co-Chair to decide what to do with reviewed submissions.
   */
  submissions: Submission[] = [];

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;
    });
  }

}
