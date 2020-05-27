import {Component, OnInit} from '@angular/core';
import {Section} from '../../models/section';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-add-section-dialog',
  templateUrl: './add-section-dialog.component.html',
  styleUrls: ['./add-section-dialog.component.css']
})
export class AddSectionDialogComponent implements OnInit {
  section: Section = {} as Section;

  constructor(
    public dialogRef: MatDialogRef<AddSectionDialogComponent>,
  ) {
  }

  ngOnInit(): void {
    this.section.startTime = new Date().toLocaleDateString();
    this.section.endTime = new Date().toLocaleDateString();
  }

  onCancelClick() {
    this.dialogRef.close();
  }

  createSection() {

  }
}
