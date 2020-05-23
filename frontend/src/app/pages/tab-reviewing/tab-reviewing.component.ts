import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {Verdict} from '../../shared/models/verdict';
import {Review} from '../../shared/models/review';
import {MatDialog} from '@angular/material/dialog';
import {ShowRecommendationsDialogComponent} from '../../shared/components/show-recommendations-dialog/show-recommendations-dialog.component';

@Component({
  selector: 'app-tab-reviewing',
  templateUrl: './tab-reviewing.component.html',
  styleUrls: ['./tab-reviewing.component.css']
})
export class TabReviewingComponent implements OnInit {
  reviews: Review[] = [
    {
      id: '1',
      submission: {
        id: '11',
        title: 'tit1',
        abstractPaper: 'abstract1',
        fullPaper: 'paper1',
        topics: 'top1, top2',
        keywords: 'key1, key2',
        finalVerdict: null,
        section: null,
        authors: null,
        likes: null,
        reviews: null,
      },
      user: null,
      verdict: Verdict.Accepted,
      recommendation: 'recommendation one'
    },
    {
      id: '2',
      submission: {
        id: '22',
        title: 'tit2',
        abstractPaper: 'abstract2',
        fullPaper: 'paper2',
        topics: 'top1, top2',
        keywords: 'key1, key2',
        finalVerdict: null,
        section: null,
        authors: null,
        likes: null,
        reviews: null,
      },
      user: null,
      verdict: null,
      recommendation: 'recommendation two'
    }
  ];
  reviewsOthers: Review[] = [];

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private dialog: MatDialog,
  ) {
  }

  ngOnInit(): void {
    // this.submissionsService.getReviews(this.authService.conference.id).subscribe((result: Review[]) => {
    //   this.reviews = result;
    // });
    //
    // this.submissionsService.getReviewsOthers(this.authService.conference.id).subscribe((result: Review[]) => {
    //   this.reviewsOthers = result;
    // });
  }

  getReviewedSubmissions() {
    return this.reviews.filter(e => e.verdict !== null);
  }

  getUnreviewedSubmissions() {
    return this.reviews.filter(e => e.verdict === null);
  }

  sendReviewButton(submissionId, reviewId, verdict: Verdict) {
    const conferenceId = this.authService.conference.id;

    this.submissionsService.sendReview(conferenceId, submissionId, reviewId, verdict);
  }

  showRecommendations(submissionId: string, yourRecommendation: string) {
    const reviews: Review[] = this.reviewsOthers.filter(e => e.submission.id === submissionId);

    this.dialog.open(ShowRecommendationsDialogComponent, {
      data: {
        recommendation: yourRecommendation,
        reviews,
      },
    });
  }
}
