import {Component, OnInit} from '@angular/core';
import {Conference} from '../../models/conference';
import {MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConferencesService} from '../../services/conferences.service';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-create-conference',
  templateUrl: './create-conference-dialog.component.html',
  styleUrls: ['./create-conference-dialog.component.css']
})
export class CreateConferenceDialogComponent implements OnInit {
  conference: Conference = {} as Conference;
  formConference: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<CreateConferenceDialogComponent>,
    private conferencesService: ConferencesService,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder
  ) {
    this.formConference = this.formBuilder.group({
      conferenceName: ['Conference 3 Man-Made', Validators.required],
      startDate: [Date.now(), Validators.required],
      endDate: ['', Validators.required],
      taxFee: ['200', Validators.compose([Validators.required, Validators.min(0)])],
      allowTwoDeadlines: [false],
      abstractDeadline: {value: '', disabled: true},
      proposalDeadline: ['', Validators.required],
      website: ['https://www.newConference.com', Validators.compose([Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')])],
      biddingDeadline: ['', Validators.required],
      evaluationDeadline: ['', Validators.required],
      nrOfReviews: ['2', Validators.compose([Validators.required, Validators.min(1)])],
      allowUpload: true,
      members: this.formBuilder.array([])
    });
  }


  /**
   * Enables an input field if user wants two deadlines.
   */
  disableSecondDeadline() {
    if (this.formConference.controls.allowTwoDeadlines.value) {
      this.formConference.controls.abstractDeadline.enable();
    } else {
      this.formConference.controls.abstractDeadline.reset();
      this.formConference.controls.abstractDeadline.disable();
    }
  }

  /**
   * Easier access to the form control array of invited members.
   */
  get conferenceMembers() {
    return this.formConference.get('members') as FormArray;
  }

  /**
   * Adds a new form control input for an invited member.
   */
  addMember() {
    this.conferenceMembers.push(this.createMemberInput());
  }

  private createMemberInput() {
    return this.formBuilder.group({
      email: ['', Validators.compose([Validators.required, Validators.email])],
      role: ['', Validators.required]
    });
  }

  removeMember(index) {
    this.conferenceMembers.removeAt(index);
  }

  onCreateClick() {
    const value = this.formConference.value;
    this.conference.name = value.conferenceName;
    this.conference.startDate = Date.parse(value.startDate) / 1000;
    this.conference.endDate = Date.parse(value.endDate) / 1000;
    this.conference.proposalDeadline = Date.parse(value.proposalDeadline) / 1000;
    this.conference.abstractDeadline = value.abstractDeadline ? Date.parse(value.abstractDeadline) / 1000 : null;
    this.conference.allowFullPaper = value.allowUpload;
    this.conference.taxFee = value.taxFee;
    this.conference.id = '';
    this.conference.evaluationDeadline = Date.parse(value.evaluationDeadline) / 1000;
    this.conference.biddingDeadline = Date.parse(value.biddingDeadline) / 1000;
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
        this.dialogRef.close(response);
      }, error: _ => {
        this.snackBar.open('Couldn\'t create conference!', ':(', {
          duration: 2000,
          panelClass: ['warning'],
        });
      }
    });
  }

  onCancelClick() {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
