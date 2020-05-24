import { Component, OnInit } from '@angular/core';
import {Conference} from '../shared/models/conference';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConferencesService} from '../shared/services/conferences.service';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-create-conference',
  templateUrl: './create-conference.component.html',
  styleUrls: ['./create-conference.component.css']
})
export class CreateConferenceComponent implements OnInit {

  allowTwoDeadlines = false;
  committeeMembers: any[] = [{
    email: '',
    memberRole: ''
  }];

  conference: Conference = {
    abstractDeadline: null,
    biddingDeadline: null,
    emails: [],
    evaluationDeadline: null,
    fullPaperDeadline: null,
    id: null,
    nrOfReviewers: 0,
    taxFee: 0,
    website: null,
    name: null,
    startDate: null,
    endDate: null
  };

  constructor(
    public dialogRef: MatDialogRef<CreateConferenceComponent>,
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
