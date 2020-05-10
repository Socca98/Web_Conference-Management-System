import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {User} from '../../shared/models/user';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Token} from '../../shared/models/token';

@Component({
  selector: 'app-login',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css'],
})
export class LoginDialogComponent implements OnInit {
  user: User = {
    username: null,
    password: null,
    fullName: null,
    affiliation: null,
    email: null,
    webpage: null,
    isChair: null,
    role: null,
  };

  constructor(
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    private loginService: AuthService,
    private snackBar: MatSnackBar,
    ) {
  }

  ngOnInit(): void {
  }

  /**
   * Sends 'this.user' to AuthService for login.
   * Login button's click calls this function.
   * In HTML, [(ngModel)] changes automatically object 'this.user' if input fields change.
   */
  onLoginClick() {
    this.loginService.loginUser(this.user).subscribe({
      next: (response: Token) => {
        localStorage.setItem('token', response.token);
        this.dialogRef.close();
        this.snackBar.open('Login successfully.', '', {
          duration: 1000
        });
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Invalid user or password.');
      }
    });
  }

  /**
   * Close login dialog.
   */
  onCancelClick() {
    this.dialogRef.close();
  }
}
