import {Component, OnInit} from '@angular/core';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {Verdict} from '../../shared/models/verdict';
import {Review} from '../../shared/models/review';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ShowRecommendationsDialogComponent} from '../../shared/components/show-recommendations-dialog/show-recommendations-dialog.component';
import {Submission} from '../../shared/models/submission';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddRecommendationDialogComponent} from '../../shared/components/add-recommendation-dialog/add-recommendation-dialog.component';

@Component({
  selector: 'app-tab-reviewing',
  templateUrl: './tab-reviewing.component.html',
  styleUrls: ['./tab-reviewing.component.css']
})
export class TabReviewingComponent implements OnInit {
  reviews: Review[] = [];
  reviewsOthers: Review[] = []; // All reviews of conference except current user (for recommendations)
  localVerdicts: Verdict[] = [];

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {

  }

  ngOnInit(): void {
    this.submissionsService.getReviews(this.authService.conference.id).subscribe((result: Review[]) => {
      // All reviews of the current user
      this.reviews = result;
    });

    this.submissionsService.getReviewsOthers(this.authService.conference.id).subscribe((result: Review[]) => {
      this.reviewsOthers = result;
    });
  }

  getReviewedSubmissions() {
    return this.reviews.filter(e => e.verdict !== Verdict.Not_Reviewed);
  }

  getUnreviewedSubmissions() {
    return this.reviews.filter(e => e.verdict === Verdict.Not_Reviewed);
  }

  openAddReviewDialog(currentReview: Review, index: number) {
    if (!this.localVerdicts[index]) {
      this.snackBar.open('Please select verdict!', 'Ok', {
        duration: 2000,
        panelClass: ['warning']
      });
      return;
    }

    const conferenceId = this.authService.conference.id;
    const selectedVerdict = this.localVerdicts[index];  // Only now take value from select box
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      conferenceId,
      currentReview,
      selectedVerdict,
    };

    this.dialog.open(AddRecommendationDialogComponent, dialogConfig).afterClosed()
      .subscribe((isSuccesful: boolean) => {
        if (isSuccesful) {
          // Update the local list of verdicts
          currentReview.verdict = this.localVerdicts[index];
        }
      });
  }

  showRecommendations(currentSubmission: Submission, yourRecommendation: string) {
    const reviewsByUserBySubmission: Review[] = this.reviewsOthers.filter(e => e.submission.id === currentSubmission.id);
    this.dialog.open(ShowRecommendationsDialogComponent, {
      data: {
        recommendation: yourRecommendation,
        reviews: reviewsByUserBySubmission,
        submission: currentSubmission,
      }
    });
  }

  downloadAbstractPaper(review) {
    this.submissionsService.downloadFile(review.submission.abstractPaper);
  }

  downloadFullPaper(review) {
    this.submissionsService.downloadFile(review.submission.fullPaper);
  }
}












