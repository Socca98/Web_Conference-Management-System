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

  constructor(
    private router: Router,
    private dialog: MatDialog,
    public authService: AuthService,
  ) {
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
}
