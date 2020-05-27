import {Component, OnInit} from '@angular/core';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {Verdict} from '../../shared/models/verdict';
import {Review} from '../../shared/models/review';
import {MatDialog} from '@angular/material/dialog';
import {ShowRecommendationsDialogComponent} from '../../shared/components/show-recommendations-dialog/show-recommendations-dialog.component';
import {Submission} from '../../shared/models/submission';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Token} from '../../shared/models/token';
import {User} from '../../shared/models/user';

@Component({
  selector: 'app-tab-reviewing',
  templateUrl: './tab-reviewing.component.html',
  styleUrls: ['./tab-reviewing.component.css']
})
export class TabReviewingComponent implements OnInit {
  reviews: Review[] = []; // All reviews of the current user
  reviewsOthers: Review[] = []; // All reviews of conference except current user
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

  sendReviewButton(currentReview: Review, index) {
    const conferenceId = this.authService.conference.id;
    if (!this.localVerdicts[index]) {
      this.snackBar.open('Please select verdict!', 'Ok', {
        duration: 2000,
        panelClass: ['warning']
      });
      return;
    }

    this.submissionsService.updateReview(conferenceId, currentReview.submission.id, currentReview.reviewId, this.localVerdicts[index])
      .subscribe({
        next: () => {
          currentReview.verdict = this.localVerdicts[index];
        },
        error: _ => {
          this.snackBar.open('Could not send review!', '', {
            duration: 2000,
            panelClass: ['warning'],
          });
        }
      });
  }

  showRecommendations(currentSubmission: Submission, yourRecommendation: string) {
    const reviewsByUserBySubmission: Review[] = this.reviewsOthers.filter(e => e.submission.id === currentSubmission.id);
    console.log(reviewsByUserBySubmission);
    this.dialog.open(ShowRecommendationsDialogComponent, {
      data: {
        recommendation: yourRecommendation,
        reviews: reviewsByUserBySubmission,
        submission: currentSubmission,
      }
    });
  }
}
