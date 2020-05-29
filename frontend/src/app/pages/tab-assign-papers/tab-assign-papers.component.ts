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
  // possibleAssignees: User[];
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

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;

      for (const submission of this.submissions) {
        this.tabs.push({submission, usersToAssign: []});
      }
    });
  }

  addSelectionToTab(tab: Tab) {
    const levelConference = this.authService.conference.nrOfReviews;
    if (tab.usersToAssign.length > levelConference) {
      alert('The conference allows the maximum number of reviews to be: ' + levelConference);
    }
    tab.usersToAssign.push(this.selection);
  }

  clickAssign(tab: Tab) {
    for (const optionUser of tab.usersToAssign) {
      const review: Review = {
        submission: tab.submission,
        user: optionUser,
      } as Review;

      this.submissionsService.createReview(this.authService.conference.id, review).subscribe({
        next: _ => {
          this.snackBar.open('Users assigned to review.', 'Ok', {
            duration: 1000,
          });
        },
        error: _ => {
          this.snackBar.open('Cannot assign users!', '', {
            duration: 1000,
            panelClass: ['warning'],
          });
        }
      });
    }
  }
}
