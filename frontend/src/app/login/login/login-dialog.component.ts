import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../shared/services/login.service';
import {User} from '../../shared/interfaces/user';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css'],
  providers: [LoginService]
})
export class LoginDialogComponent implements OnInit {
  user: User = {
    username: null,
    password: null,
    affiliation: null,
    email: null,
    name: null,
    webpage: null,
    role: null,
  };

  constructor(
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    private loginService: LoginService) {
  }

  ngOnInit(): void {
  }

  /**
   * Sends 'this.user' to LoginService.
   * Login button's click calls this function.
   * [(ngModel)] changes automatically the object 'this.user' if input fields change.
   */
  onLoginClick() {
    this.loginService.loginUser(this.user).subscribe({
      next: (response: string) => {
        alert(response);
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
