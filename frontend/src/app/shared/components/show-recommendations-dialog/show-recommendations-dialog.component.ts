import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Review} from '../../models/review';
import {Submission} from '../../models/submission';

@Component({
  selector: 'app-show-recommendations-dialog',
  templateUrl: './show-recommendations-dialog.component.html',
  styleUrls: ['./show-recommendations-dialog.component.css']
})
export class ShowRecommendationsDialogComponent implements OnInit {
  yourRecommendation: string;
  reviewsOthers: Review[] = [];
  submission: Submission = {} as Submission;

  constructor(
    public dialogRef: MatDialogRef<ShowRecommendationsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
  }

  ngOnInit(): void {
    // if (!this.data.reviews || !this.data.yourRecommendation || !this.data.reviews) {
    //   throw Error('Invalid input data for recommendations dialog!');
    // }

    this.reviewsOthers = this.data.reviews;
    this.yourRecommendation = this.data.recommendation;
    this.submission = this.data.submission; // All reviews share the same submission
  }

  onCloseClick() {
    this.dialogRef.close();
  }
}
