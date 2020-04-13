import {Component} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn = true;

  constructor(
    private router: Router,
    public dialog: MatDialog) {
  }

  openLoginDialog() {
    this.dialog.open(LoginDialogComponent);
  }

  logout() {
    this.isLoggedIn = false;
    this.router.navigate(['']).then(_ => true);
  }
}
