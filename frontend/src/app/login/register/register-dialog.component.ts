import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/models/user';
import {AuthService} from '../auth.service';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css'],
})
export class RegisterDialogComponent implements OnInit {
  user: User = {} as User;

  constructor(
    public dialogRef: MatDialogRef<RegisterDialogComponent>,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
  }

  /**
   * Sends 'this.user' to AuthService.
   * Click on Register button calls this function.
   */
  onRegisterClick() {
    this.authService.registerUser(this.user).subscribe({
      next: (response: User) => {
        alert(response);
        this.dialogRef.close();
        this.snackBar.open('User created.', 'Ok', {
          duration: 1000
        });
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Cannot create user!');
      }
    });
  }

  /**
   * Close register dialog.
   */
  onCancelClick() {
    this.dialogRef.close();
  }
}
