import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {User} from '../../shared/models/user';
import {ConferencesService} from '../../shared/services/conferences.service';
import {Conference} from '../../shared/models/conference';
import {Role} from '../../shared/models/role';
import {Review} from '../../shared/models/review';

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
  anotherReviewer: string;

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private conferencesService: ConferencesService,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;

      // Populate reviewers only after we get the submissions
      // Should check if conference.id is null before using it?
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
    console.log({
      submission,
      reviews: submission.reviews,
      possibleReviewers: this.submissionPossibleReviewers,
      submissions: this.submissions,
    });
    return submission.reviews.length !== 0;
  }


  sendToReviewer() {

  }

  requestDiscussion() {

  }

  chooseForThem() {

  }

}
