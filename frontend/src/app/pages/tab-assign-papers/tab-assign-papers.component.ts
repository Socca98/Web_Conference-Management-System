import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {User} from '../../shared/models/user';
import {AuthService} from '../../login/auth.service';
import {Review} from '../../shared/models/review';
import {MatSnackBar} from '@angular/material/snack-bar';

interface Tab {
  submission: Submission;
  usersToAssign: User[];
  possibleAssignees: User[];  // Users that liked but do not have review.
}

@Component({
  selector: 'app-tab-assign-papers',
  templateUrl: './tab-assign-papers.component.html',
  styleUrls: ['./tab-assign-papers.component.css']
})
export class TabAssignPapersComponent implements OnInit {
  submissions: Submission[] = [];
  possibleOptionUsers: User[] = []; // Users shown in select that can be assigned
  tabs: Tab[] = [];
  selection: User;
  disableButtons = [];

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;

      // We cannot assign to users that already have a Review in progress.
      // We filter users that are both in 'likes' and 'reviews' list of a submission

      for (const submission of this.submissions) {
        const possibleUsers = submission.likes.filter(userFromLikes =>
          !submission.reviews.some(review => userFromLikes.email === review.user.email)
        );
        this.tabs.push({submission, usersToAssign: [], possibleAssignees: possibleUsers});

        // Initialize array of booleans for buttons

        this.disableButtons.push(false);
      }
    });
  }

  addSelectionToTab(tab: Tab) {
    const levelConference = this.authService.conference.nrOfReviews;

    // Check level of conference
    if (tab.usersToAssign.length >= levelConference) {
      this.snackBar.open('The conference allows the maximum number of reviewers to be: ' + levelConference, '', {
        duration: 2000,
        panelClass: ['warning'],
      });
      return;
    }

    // Check if already in list
    if (tab.usersToAssign.some(e => e.username === this.selection.username)) {
      this.snackBar.open('User already added for this!', '', {
        duration: 1000,
        panelClass: ['warning'],
      });
      return;
    }

    tab.usersToAssign.push(this.selection);
  }

  clickAssign(tab: Tab, indexOfTab) {
    for (const optionUser of tab.usersToAssign) {
      const review: Review = {
        submission: tab.submission,
        user: optionUser,
      } as Review;

      this.submissionsService.createReview(this.authService.conference.id, review).subscribe({
        next: _ => {
          this.disableButtons[indexOfTab] = true;
          window.location.reload();
          this.snackBar.open('Users assigned to review.', 'Ok', {
            duration: 1000,
          });

        },
        error: err => {
          this.snackBar.open('Failed! ' + err, '', {
            duration: 1000,
            panelClass: ['warning'],
          });
        }
      });
    }
  }
}
