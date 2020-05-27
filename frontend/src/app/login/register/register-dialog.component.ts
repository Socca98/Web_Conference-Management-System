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
      next: (_: User) => {
        this.dialogRef.close();
        this.snackBar.open('User created.', '', {
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
  }

  /**
   * Close register dialog.
   */
  onCancelClick() {
    this.dialogRef.close();
  }
}
