import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {AuthService} from '../../login/auth.service';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {Verdict} from '../../shared/models/verdict';

@Component({
  selector: 'app-tab-results',
  templateUrl: './tab-results.component.html',
  styleUrls: ['./tab-results.component.css']
})
export class TabResultsComponent implements OnInit {
  submissionsReviewed: Submission[] = []; // Submissions of this user that have finalVerdict

  constructor(
    private authService: AuthService,
    private submissionsService: SubmissionsService,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      // Filter and keep those with final verdict.
      this.submissionsReviewed = result.filter(e => e.finalVerdict != null && e.finalVerdict !== Verdict.Not_Reviewed);
    });
  }

  /**
   * Returns true if the paper is accepted as final verdict.
   * @param submission Submission to be checked
   */
  isAccepted(submission: Submission): boolean {
    return submission.finalVerdict === Verdict.Weak_Accept ||
      submission.finalVerdict === Verdict.Accepted ||
      submission.finalVerdict === Verdict.Strong_Accept;
  }

}
