import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConferencesService} from '../../services/conferences.service';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-create-conference',
  templateUrl: './create-conference-dialog.component.html',
  styleUrls: ['./create-conference-dialog.component.css']
})
export class CreateConferenceDialogComponent implements OnInit {
  allowTwoDeadlines = false;
  committeeMembers: any[] = [{
    email: '',
    memberRole: ''
  }];
  emails: [] = [];
  conference: Conference = {} as Conference;

  constructor(
    public dialogRef: MatDialogRef<CreateConferenceDialogComponent>,
    private conferencesService: ConferencesService,
    private snackBar: MatSnackBar) {
  }

  onCreateClick() {
    this.snackBar.open('Yay!');
  }

  onCancelClick() {
    this.dialogRef.close();
  }

  addCommitteeMember() {
    this.committeeMembers.push({
      email: '',
      memberRole: ''
    });
  }

  ngOnInit(): void {
  }

}
