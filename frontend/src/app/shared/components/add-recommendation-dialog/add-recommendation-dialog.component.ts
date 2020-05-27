import {Component, Inject, OnInit} from '@angular/core';
import {SubmissionsService} from '../../services/submissions.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Review} from '../../models/review';

@Component({
  selector: 'app-add-recommendation-dialog',
  templateUrl: './add-recommendation-dialog.component.html',
  styleUrls: ['./add-recommendation-dialog.component.css']
})
export class AddRecommendationDialogComponent implements OnInit {
  review: Review = {} as Review; // New fields for the review

  constructor(
    private submissionsService: SubmissionsService,
    public dialogRef: MatDialogRef<AddRecommendationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
    private snackBar: MatSnackBar,
  ) {
    this.review = {...data.currentReview}; // spread operator, like deep copy
    this.review.verdict = this.data.selectedVerdict;
  }

  ngOnInit(): void {
  }

  sendReview() {
    const conferenceId = this.data.conferenceId;
    this.submissionsService.updateReview(conferenceId, this.review.submission.id, this.review)
      .subscribe({
        next: () => {
          this.dialogRef.close(true);
          this.snackBar.open('Review sent.', 'Ok', {
            duration: 1000,
          });
        },
        error: _ => {
          this.snackBar.open('Could not send review!', '', {
            duration: 2000,
            panelClass: ['warning'],
          });
        }
      });
  }

  onCloseClick() {
    this.dialogRef.close();
  }
}

