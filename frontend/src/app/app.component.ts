import {Component, EventEmitter, Output} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {Router} from '@angular/router';
import {RegisterDialogComponent} from './login/register/register-dialog.component';
import {AuthService} from './login/auth.service';
import {CreateConferenceDialogComponent} from './shared/components/create-conference-dialog/create-conference-dialog.component';
import {ColorSchemeService} from './shared/services/color-scheme.service';
import {Theme} from './shared/models/theme';
import {Role} from './shared/models/role';
import {Conference} from './shared/models/conference';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService]
})
export class AppComponent {
  helperDeadline: number;
  deadlineTypeLabel = '';
  @Output() handleNewConference = new EventEmitter<Conference>();  // Send the new conference to HomePage list


  constructor(
    private router: Router,
    private dialog: MatDialog,
    public authService: AuthService,
    public colorSchemeService: ColorSchemeService,
  ) {
    this.labelDeadline();

    // Load Color Scheme (Theme)
    this.colorSchemeService.load();
  }


  /**
   * Clicking the left up corner icon changes the theme of the application.
   */
  switchTheme() {
    if (this.colorSchemeService.currentActive() === Theme.Alien) {
      this.colorSchemeService.update(Theme.Light);
      this.colorSchemeService.mainImageUrl = '../assets/icon1.png';
      this.colorSchemeService.conferenceComponentImageUrl = '../../../../assets/icon3-svg.svg';
    } else {
      this.colorSchemeService.update(Theme.Alien);
      this.colorSchemeService.mainImageUrl = '../assets/alien.svg';
      this.colorSchemeService.conferenceComponentImageUrl = '../../../../assets/icon3-alien.svg';
    }
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
    }).afterClosed()
      .subscribe((newConference: Conference) => {
        if (newConference) {
          // EventEmitter<any>(), any helps at sending more parameters as json
          // this.handleNewConference.emit(newConference);
          // ! We can reach Home Component with the new Conference sending route param
        }
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
   * Check if we display a specific navbar tab depending on the role.
   * I really wish there is an easier way instead of 1 mil ifs
   * @param tabName Name of the tab as a string.
   */
  isRoleOk(tabName: string): boolean {
    const currentUserRole = this.authService.getUserRole();

    if (currentUserRole === null) {
      return false;
    }

    switch (currentUserRole) {
      case Role.Chair:
        return true;

      case Role.CoChair:
        if (tabName === 'Submissions' || tabName === 'Results') {
          return false;
        }
        break;

      case Role.Author:
        if (tabName === 'Bidding' ||
          tabName === 'Assign papers' ||
          tabName === 'Reviewing' ||
          tabName === 'Evaluations') {
          return false;
        }
        break;

      case Role.PC_Member:
        if (tabName === 'Assign papers' || tabName === 'Evaluations') {
          return false;
        }
        break;

      case Role.SC_Member:
        if (tabName === 'Submissions' ||
          tabName === 'Results' ||
          tabName === 'Bidding' ||
          tabName === 'Assign papers' ||
          tabName === 'Reviewing' ||
          tabName === 'Evaluations') {
          return false;
        }
        break;

      default:
        return false;
    }
    if (tabName === 'Create conference') {
      return false;
    }
    return true;
  }
}
