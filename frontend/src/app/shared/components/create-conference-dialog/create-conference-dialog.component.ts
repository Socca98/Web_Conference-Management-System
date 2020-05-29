import { Component, OnInit } from '@angular/core';
import {Conference} from '../../models/conference';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConferencesService} from '../../services/conferences.service';
import {MatSelectModule} from '@angular/material/select';
import {AbstractControl, Form, FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpParams} from "@angular/common/http";

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

  onCreateClick(value: any) {
    this.snackBar.open('Yay!');
    const body = new HttpParams()
      .set('name', value.conferenceName)
      .set('startDate', String(Date.parse(value.startDate)))
      .set('startDate', String(Date.parse(value.endDate)))
      .set('abstractDeadline', String(Date.parse(value.abstractDeadline)))
      .set('proposalDeadline', String(Date.parse(value.proposalDeadline)))
      .set('website', value.website)
      .set('allowFullPaper', value.allowUpload)
      .set('taxFee', value.taxFee)
      .set('nrOfReviews', value.nrOfReviews)
      .set('users', value.members);
    console.log(body.toString());
    this.conferencesService.addConference(body);
  }

  onCancelClick() {
    this.dialogRef.close();
  }

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
