import {Component} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {Router} from '@angular/router';
import {RegisterDialogComponent} from './login/register/register-dialog.component';
import {AuthService} from './login/auth.service';
import {CreateConferenceDialogComponent} from './shared/components/create-conference-dialog/create-conference-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})
export class AppComponent {
  helperDeadline: number;
  deadlineTypeLabel = '';

  constructor(
    private router: Router,
    private dialog: MatDialog,
    public authService: AuthService,
  ) {
    this.labelDeadline();
  }

  openLoginDialog() {
    this.dialog.open(LoginDialogComponent);
  }

  openRegisterDialog() {
    this.dialog.open(RegisterDialogComponent);
  }

  isOnHomePage() {
    return this.router.url === '/';
  }

  openCreateConferenceDialog() {
    this.dialog.open(CreateConferenceDialogComponent);
  }

  labelDeadline() {
    this.router.events.subscribe(_ => {
      if (!this.authService.conference) {
        return;
      }
      switch (this.router.url) {
        case '/conference/results': {
          this.helperDeadline = this.authService.conference.evaluationDeadline * 1000;
          this.deadlineTypeLabel = 'Evaluations: ';
          break;
        }
        case '/conference/reviewing': {
          this.helperDeadline = this.authService.conference.evaluationDeadline * 1000;
          this.deadlineTypeLabel = 'Evaluations: ';
          break;
        }
        case '/conference/evaluations': {
          this.helperDeadline = this.authService.conference.evaluationDeadline * 1000;
          this.deadlineTypeLabel = 'Evaluations: ';
          break;
        }
        case '/conference/bidding': {
          this.helperDeadline = this.authService.conference.biddingDeadline * 1000;
          this.deadlineTypeLabel = 'Bidding: ';
          break;
        }
        case '/conference/submissions': {
          this.helperDeadline = this.authService.conference.abstractDeadline * 1000;
          this.deadlineTypeLabel = 'Abstracts: ';
          break;
        }
        default: {
          this.helperDeadline = null;
          this.deadlineTypeLabel = '';

          break;
        }
      }
    });
  }
}
