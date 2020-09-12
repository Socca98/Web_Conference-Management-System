import {Component} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {Router} from '@angular/router';
import {RegisterDialogComponent} from './login/register/register-dialog.component';
import {AuthService} from './login/auth.service';
import {CreateConferenceDialogComponent} from './shared/components/create-conference-dialog/create-conference-dialog.component';
import {ColorSchemeService} from './shared/services/color-scheme.service';
import {Theme} from './shared/models/theme';

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
    public colorSchemeService: ColorSchemeService,
  ) {
    this.labelDeadline();

    // Load Color Scheme (Theme)
    this.colorSchemeService._setColorScheme('alien');
    this.colorSchemeService.load();
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
    this.dialog.open(CreateConferenceDialogComponent, {
      height: '90vh',
      width: '80vw'
    });
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

  /**
   * Clicking the 7left up corner icon changes the theme of the application.
   */
  switchTheme() {
    if (this.colorSchemeService.currentActive() === Theme.Alien) {
      this.colorSchemeService.update(Theme.Light);
      this.colorSchemeService.mainImageUrl = '../assets/icon1.png';
    } else {
      this.colorSchemeService.update(Theme.Alien);
      this.colorSchemeService.mainImageUrl = '../assets/alien.svg';
    }
  }
}
