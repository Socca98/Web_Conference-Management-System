import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AuthService} from '../../../login/auth.service';

@Component({
  selector: 'app-attend-section-dialog',
  templateUrl: './attend-section-dialog.component.html',
  styleUrls: ['./attend-section-dialog.component.css']
})
export class AttendSectionDialogComponent implements OnInit {
  taxFee: number = this.authService.conference.taxFee;

  constructor(
    public dialogRef: MatDialogRef<AttendSectionDialogComponent>,
    private snackBar: MatSnackBar,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
  }

  onCancelClick() {
    this.dialogRef.close(false);
  }

  onPayClick() {
    this.snackBar.open('Successfully payed with PayPal.', 'Ok', {
      duration: 2000,
    });
    this.dialogRef.close(true);
  }
}
