import {Component, OnInit} from '@angular/core';
import {Section} from '../../models/section';
import {MatDialogRef} from '@angular/material/dialog';
import {SubmissionsService} from '../../services/submissions.service';
import {AuthService} from '../../../login/auth.service';
import {Submission} from '../../models/submission';

@Component({
  selector: 'app-add-section-dialog',
  templateUrl: './add-section-dialog.component.html',
  styleUrls: ['./add-section-dialog.component.css']
})
export class AddSectionDialogComponent implements OnInit {
  section: Section = {} as Section;
  acceptedSubmissions: Submission[] = [];

  constructor(
    public dialogRef: MatDialogRef<AddSectionDialogComponent>,
    private submissionsService: SubmissionsService,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    // Get all accepted papers to show in select box
    const conferenceId = this.authService.conference.id;
    this.submissionsService.getAcceptedSubmissions(conferenceId);

    this.section.startTime = new Date().toLocaleDateString();
    this.section.endTime = new Date().toLocaleDateString();
  }

  onCancelClick() {
    this.dialogRef.close();
  }

  createSection() {

  }
}
