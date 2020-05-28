import {Component, Inject, OnInit} from '@angular/core';
import {User} from '../../shared/models/user';
import {AuthService} from '../auth.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css'],
})
export class RegisterDialogComponent implements OnInit {
  user: User = {} as User;
  invitationLink: string;

  constructor(
    public dialogRef: MatDialogRef<RegisterDialogComponent>,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private data,
  ) {
    if (data) {
      this.invitationLink = data.linkId; // spread operator, like deep copy
    }
  }

  ngOnInit(): void {
  }

  /**
   * Sends 'this.user' to AuthService.
   * Click on Register button calls this function.
   */
  onRegisterClick() {
    if (this.invitationLink) {
      this.authService.registerUserInvitationLink(this.user, this.invitationLink).subscribe({
        next: (_: User) => {
          this.dialogRef.close();
          this.snackBar.open('User created with invitation link.', '', {
            duration: 1000
          });
        },
        error: _ => {
          this.snackBar.open('Cannot create user!', 'Ok', {
            duration: 1000,
            panelClass: ['warning'],
          });
        }
      });
    } else {
      this.authService.registerUser(this.user).subscribe({
        next: (_: User) => {
          this.dialogRef.close();
          this.snackBar.open('User created.', '', {
            duration: 1000
          });
        },
        error: _ => {
          this.snackBar.open('Username taken!', 'Ok', {
            duration: 1000,
            panelClass: ['warning'],
          });
        }
      });
    }
  }

  /**
   * Close register dialog.
   */
  onCancelClick() {
    this.dialogRef.close();
  }
}
