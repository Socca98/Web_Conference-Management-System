import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {User} from '../../shared/models/user';

@Component({
  selector: 'app-tab-bidding',
  templateUrl: './tab-bidding.component.html',
  styleUrls: ['./tab-bidding.component.css']
})
export class TabBiddingComponent implements OnInit {
  submissions: Submission[];
  likes: boolean[] = [];  // true - liked/checked, false - unliked/unchecked

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private snackbar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;

      // Check local those that are in DB liked
      this.submissions.forEach((s: Submission, index) => {
        const isLiked = s.likes.some((e: User) => e.username === this.authService.user.username);
        if (isLiked) {
          this.likes[index] = true;
        }
      });
    });
  }

  onToggle($event, submission: Submission, index: number) {
    const conferenceId = this.authService.conference.id;
    const bidding = $event.checked;  // take submitted click, not local one

    if (bidding) {
      this.submissionsService.likeSubmission(conferenceId, submission.id).subscribe({
        next: _ => {
        },
        error: err => {
          this.snackbar.open('Could not send like!', '', {
            duration: 1000,
            panelClass: ['warning'],
          });
          this.likes[index] = !this.likes[index];  // update local
          console.log(err.error.message);
        }
      });
    } else {
      this.submissionsService.unlikeSubmission(conferenceId, submission.id).subscribe({
        next: _ => {
        },
        error: err => {
          this.snackbar.open('Could not send unlike!', '', {
            duration: 1000,
            panelClass: ['warning'],
          });
          this.likes[index] = !this.likes[index];  // update local
          console.log(err.error.message);
        }
      });
    }
  }

  showI(i: number) {
    const sd = i;
    console.log(i);
  }

  downloadAbstractPaper(i) {
    this.submissionsService.downloadFile(this.submissions[i].abstractPaper);
  }

  downloadFullPaper(i: number) {
    this.submissionsService.downloadFile(this.submissions[i].fullPaper);
  }
}
