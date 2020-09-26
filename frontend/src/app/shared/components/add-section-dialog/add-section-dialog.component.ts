import {Component, OnInit} from '@angular/core';
import {Section} from '../../models/section';
import {MatDialogRef} from '@angular/material/dialog';
import {SubmissionsService} from '../../services/submissions.service';
import {AuthService} from '../../../login/auth.service';
import {Submission} from '../../models/submission';
import {Verdict} from '../../models/verdict';
import {User} from '../../models/user';
import {MatSelectChange} from '@angular/material/select';
import {MatOptionSelectionChange} from '@angular/material/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Conference} from '../../models/conference';
import {Role} from '../../models/role';
import {ConferencesService} from '../../services/conferences.service';

@Component({
  selector: 'app-add-section-dialog',
  templateUrl: './add-section-dialog.component.html',
  styleUrls: ['./add-section-dialog.component.css']
})
export class AddSectionDialogComponent implements OnInit {
  section: Section = {} as Section;
  possibleSectionChair = [] as User[];  // Possible section chairs
  acceptedSubmissions: Submission[] = [];
  minStartTime = new Date();  // Start time of a section cannot be less than 'now' time
  defaultDateRange = [new Date(), new Date()]; // This is data bound to the mat input for data range.
  alreadySelected: boolean[] = []; // Options selected that should not show in future Choose paper options list.
  previousOption: number[] = []; // (a[0] == 2) -> option index j == 2 is currently selected on mat-select index i == 0

  constructor(
    public dialogRef: MatDialogRef<AddSectionDialogComponent>,
    private submissionsService: SubmissionsService,
    private conferencesService: ConferencesService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
    this.section.title = 'Sushi';
    this.section.seats = 2;
    this.section.speakers = [] as User[];
    this.section.submissions = [] as Submission[];
    this.section.sectionChair = {} as User;
    this.section.speakers.push({} as User);
  }

  ngOnInit(): void {
    // Get all accepted papers to show in select box
    const conferenceId = this.authService.conference.id;

    this.submissionsService.getSubmissions(conferenceId).subscribe({
      next: (result: Submission[]) => {
        // Filter those with final verdict 'Accept' and which do not belong to any Section
        this.acceptedSubmissions = result.filter(e =>
          this.isAcceptedVerdict(e.finalVerdict) && !e.section);

        // All these filters are very inefficient, but we're learning :)

        this.conferencesService.getConference(this.authService.conference.id).subscribe((resultConference: Conference) => {
          // Get users for section chair role
          this.possibleSectionChair = resultConference.users.filter(e =>
            (e.role === Role.PC_Member) || (e.role === Role.SC_Member) || (e.role === Role.CoChair) || (e.role === Role.Chair));
        });
      },
      error: err => {
        console.log(err);
      }
    });
  }

  /**
   * Returns true if the verdict is an Accept type.
   * Should not be null or not reviewed.
   * @param verdict The verdict we check.
   */
  isAcceptedVerdict(verdict: Verdict): boolean {
    return !(verdict === null || verdict === Verdict.Not_Reviewed || verdict === Verdict.Weak_Reject ||
      verdict === Verdict.Rejected || verdict === Verdict.Strong_Rejected);
  }

  identify(index, item) {
    return item.id;
  }

  /**
   * Increment ngFor for the speakers + their submission list.
   */
  incrementNumberOfSpeakers() {
    const user = {} as User;
    this.section.speakers.push(user);
  }

  /**
   * Update this.section speakers and presented papers
   * with the corresponding Submission + first of its authors
   * which will represent one of the presenter (speaker) and the presented paper
   * @param $event Contains the selected submission [0]. The index j of the selected option [0].
   * @param i Contains the index of which mat-select we operate on.
   */
  updateSpeakerSubmission($event: MatSelectChange, i: number) {
    // Check if its None option, reset values
    if ($event.value[1] === -1) {
      this.alreadySelected[this.previousOption[i]] = false;
      this.section.speakers[i] = {} as User;
      this.section.submissions[i] = {} as Submission;
      return;
    }
    // Populate local object that is sent with the request
    this.section.speakers[i] = {
      email: $event.value[0].authors[0].email
    } as User;
    this.section.submissions[i] = {
      id: $event.value[0].id
    } as Submission;

    // Disable selected option
    this.alreadySelected[$event.value[1]] = true;
    // Enable previous option and record the current option
    this.alreadySelected[this.previousOption[i]] = false;
    this.previousOption[i] = $event.value[1];
  }

  validateSection(): boolean {
    // Check empty fields
    if (this.section.title === null || this.section.seats === null || this.section.seats < 1 ||
      this.defaultDateRange === [] || this.section.speakers === null ||
      this.section.submissions === null || this.section.sectionChair === null) {
      return false;
    }

    // Check if there are empty objects in the section (ex: [{ }] ), due to pressing the '+' button
    if (this.section.speakers.some(author => Object.keys(author).length === 0) ||
      this.section.submissions.some(submission => Object.keys(submission).length === 0)) {
      return false;
    }

    // Check if there are duplicates; is done by disabling options
    // Check if section chair is not one of the speakers
    return true;
  }

  createSection() {
    if (!this.validateSection()) {
      this.snackBar.open('Invalid input fields!', '', {
        duration: 1000,
        panelClass: ['warning'],
      });
      return false;
    }

    // Assign start time and end time from date range without milliseconds
    this.section.startTime = Math.floor(this.defaultDateRange[0].getTime() / 1000);
    this.section.endTime = Math.floor(this.defaultDateRange[1].getTime() / 1000);

    // Send the request
    const conferenceId = this.authService.conference.id;
    this.conferencesService.addSection(conferenceId, this.section).subscribe({
      next: (result: Section) => {
        this.snackBar.open('Section created.', 'Ok', {
          duration: 1000
        });
        this.dialogRef.close(result);
      },
      error: err => {
        this.snackBar.open('Error! ' + err.message, 'Ok', {
          duration: 2000
        });
      }
    });
  }

  onCancelClick() {
    this.dialogRef.close();
  }


}
