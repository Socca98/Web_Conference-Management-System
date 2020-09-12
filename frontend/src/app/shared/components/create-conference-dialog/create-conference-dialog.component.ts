import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConferencesService} from '../../services/conferences.service';
import {MatSelectModule} from '@angular/material/select';
import {AbstractControl, Form, FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpParams} from '@angular/common/http';
import {Token} from '../../models/token';
import {User} from '../../models/user';

@Component({
  selector: 'app-create-conference',
  templateUrl: './create-conference-dialog.component.html',
  styleUrls: ['./create-conference-dialog.component.css']
})
export class CreateConferenceDialogComponent implements OnInit {
  conference: Conference = {} as Conference;

  constructor(
    public dialogRef: MatDialogRef<CreateConferenceDialogComponent>,
    private conferencesService: ConferencesService,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder
  ) {
    this.formConference = this.formBuilder.group({
      conferenceName: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      taxFee: ['', Validators.compose([Validators.required, Validators.min(0)])],
      allowTwoDeadlines: [false],
      abstractDeadline: {value: '', disabled: true},
      proposalDeadline: ['', Validators.required],
      website: ['', Validators.compose([Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')])],
      biddingDeadline: ['', Validators.required],
      evaluationDeadline: ['', Validators.required],
      nrOfReviews: ['', Validators.compose([Validators.required, Validators.min(1)])],
      allowUpload: false,
      members: this.formBuilder.array([])
    });
  }

  formConference: FormGroup;

  onCreateClick() {
    const value = this.formConference.value;
    this.conference.name = value.conferenceName;
    this.conference.proposalDeadline = value.proposalDeadline.getTime;
    this.conference.abstractDeadline = value.abstractDeadline.getTime;
    this.conference.allowFullPaper = value.allowUpload;
    this.conference.taxFee = value.taxFee;
    this.conference.id = '';
    this.conference.evaluationDeadline = value.evaluationDeadline.getTime;
    this.conference.biddingDeadline = value.biddingDeadline.getTime;
    this.conference.website = value.website;
    this.conference.nrOfReviews = value.nrOfReviews;
    this.conference.users = value.members;
    console.log(this.conference);
    this.conferencesService.addConference(this.conference).subscribe({
      next: (response: Conference) => {
        this.dialogRef.close();
        this.snackBar.open('Conference created successfully!', '', {
        duration: 1000
        });
        }, error: _ => {
        this.snackBar.open('Couldn\'t create conference :(', '', {
          duration: 2000,
          panelClass: ['warning'],
        });
      }
    });
  }

  onCancelClick() {
    this.dialogRef.close();
  }

  /**
   * Enables an input field if user wants two deadlines.
   */
  disableSecondDeadline() {
    if (this.formConference.controls.allowTwoDeadlines.value) {
      this.formConference.controls.abstractDeadline.enable();
    } else {
      this.formConference.controls.abstractDeadline.disable();
    }
  }

  ngOnInit(): void {
  }

  addMember() {
    (this.formConference.controls.members as FormArray).push(this.createMemberInput());
  }

  private createMemberInput() {
    return this.formBuilder.group({
      email: ['', Validators.compose([Validators.required, Validators.email])],
      role: ['', Validators.required]
    });
  }

  removeEntry(index) {
    (this.formConference.controls.members as FormArray).removeAt(index);
  }
}
