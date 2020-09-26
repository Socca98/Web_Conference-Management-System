import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {AuthService} from '../../login/auth.service';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {Verdict} from '../../shared/models/verdict';
import {MatDialog} from '@angular/material/dialog';
import {ShowRecommendationsDialogComponent} from '../../shared/components/show-recommendations-dialog/show-recommendations-dialog.component';

@Component({
  selector: 'app-tab-results',
  templateUrl: './tab-results.component.html',
  styleUrls: ['./tab-results.component.css']
})
export class TabResultsComponent implements OnInit {
  submissionsReviewed: Submission[] = []; // Submissions of this user that have finalVerdict
  acceptedClass = 'accepted submission-card';
  rejectedClass = 'rejected submission-card';

  constructor(
    private authService: AuthService,
    private submissionsService: SubmissionsService,
    private dialog: MatDialog,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      const currentUserEmail = this.authService.user.email;
      // Filter and keep those with final verdict which belong to current user
      this.submissionsReviewed = result.filter(e => e.finalVerdict != null &&
        e.finalVerdict !== Verdict.Not_Reviewed &&
        e.authors.some(author => author.email === currentUserEmail)
      );
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

  chooseColorClass(submission) {
    if (this.isAccepted(submission)) {
      return this.acceptedClass;
    }
    return this.rejectedClass;
  }

  chooseMessageTooltip(submission) {
    if (this.isAccepted(submission)) {
      return '';
    }
    return 'We are sorry, but this submission did not pass our reviews. Good luck with other ones!';
  }

  openRecommendations(currentSubmission: Submission) {
    this.dialog.open(ShowRecommendationsDialogComponent, {
      data: {
        recommendation: null,
        reviews: currentSubmission.reviews,
        submission: currentSubmission,
      }
    });
  }

  downloadAbstractPaper(i) {
    this.submissionsService.downloadFile(this.submissionsReviewed[i].abstractPaper);
  }

  downloadFullPaper(i: number) {
    this.submissionsService.downloadFile(this.submissionsReviewed[i].fullPaper);
  }
}
