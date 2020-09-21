import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {User} from '../../shared/models/user';
import {ConferencesService} from '../../shared/services/conferences.service';
import {Conference} from '../../shared/models/conference';
import {Role} from '../../shared/models/role';
import {Verdict} from '../../shared/models/verdict';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Review} from '../../shared/models/review';
import {MatSelectChange} from '@angular/material/select';

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
  submissionPossibleReviewers: Array<User[]> = [];   // Reviewers that did not review submission[i]
  anotherReviewersEmail: string[] = []; // 'Another reviewer' for each submission tab
  localVerdicts: Verdict[] = [];


  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private conferencesService: ConferencesService,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;
      // Populate reviewers only after we get the submissions
      this.conferencesService.getConference(this.authService.conference.id).subscribe((resultConference: Conference) => {
        // Get users only with PC Member role
        const pcMembers = resultConference.users.filter(e => e.role === Role.PC_Member);

        this.submissions.forEach(s => {
          // Get PC Members which are not reviewers of this submission 's'
          const possibleReviewers = pcMembers.filter(e => !s.reviews.some(review => review.user.email === e.email));
          this.submissionPossibleReviewers.push(possibleReviewers);
        });
      });
    });
  }

  /**
   * Checks if the submission has any reviews.
   * True if it has.
   * @param submission Current submission for checking
   */
  hasReviews(submission: Submission): boolean {
    return submission.reviews.length !== 0;
  }

  sendToReviewer(currentSubmission: Submission, index) {
    const newReviewerToAdd = (this.anotherReviewersEmail)[index];

    // Check if a reviewer was selected in the mat-select
    if (!newReviewerToAdd) {
      this.snackBar.open('Please select reviewer!', 'Ok', {
        duration: 1000,
        panelClass: ['warning']
      });
      return;
    }

    // Check if this submission already has created a review for the newReviewerToAdd
    if (this.submissions[index].reviews.some(r => r.user.email === newReviewerToAdd)) {
      this.snackBar.open('Reviewer already exists!', 'Ok', {
        duration: 2000,
        panelClass: ['warning']
      });
      return;
    }

    const review: Review = {
      submission: currentSubmission,
      user: {
        email: newReviewerToAdd
      } as User,
    } as Review;

    this.submissionsService.createReview(this.authService.conference.id, review).subscribe({
      next: (response: Review) => {
        this.snackBar.open('Users assigned to review.', 'Ok', {
          duration: 1000,
        });
        currentSubmission.reviews.push(response);
        (this.anotherReviewersEmail)[index] = null;
      },
      error: _ => {
        this.snackBar.open('Cannot assign users!', '', {
          duration: 1000,
          panelClass: ['warning'],
        });
      }
    });
  }

  requestDiscussion(submission: Submission) {
    const conferenceId = this.authService.conference.id;
    for (const review of submission.reviews) {
      review.verdict = Verdict.Not_Reviewed;
      review.recommendation = '';
      this.submissionsService.updateReview(conferenceId, submission.id, review).subscribe({
        next: _ => {
          this.snackBar.open('All reviews reset.', 'Ok', {
            duration: 1000,
          });
        },
        error: _ => {
          // this.snackBar.open('Could not send review!', '', {
          //   duration: 2000,
          //   panelClass: ['warning'],
          // });
        }
      });
    }
  }

  chooseForThem(submissionId: string, index: number) {
    if (!this.localVerdicts[index]) {
      this.snackBar.open('Please select final verdict!', 'Ok', {
        duration: 2000,
        panelClass: ['warning']
      });
      return;
    }

    const conferenceId = this.authService.conference.id;
    const selectedVerdict = this.localVerdicts[index];
    this.submissionsService.sendFinalVerdict(conferenceId, submissionId, selectedVerdict).subscribe({
      next: () => {
        this.snackBar.open('Final verdict sent.', 'Ok', {
          duration: 1000,
        });

        // If success, also create the Review so Chair/Co-Chair can see it in reviews tab.
      },
      error: _ => {
        this.snackBar.open('Could not send final verdict!', '', {
          duration: 2000,
          panelClass: ['warning'],
        });
      }
    });
  }

  downloadAbstractPaper(i) {
    this.submissionsService.downloadFile(this.submissions[i].abstractPaper);
  }

  downloadFullPaper(i: number) {
    this.submissionsService.downloadFile(this.submissions[i].fullPaper);
  }
}
