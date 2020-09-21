import {Component, Inject, OnInit} from '@angular/core';
import {User} from '../../models/user';
import {Submission} from '../../models/submission';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {SubmissionsService} from '../../services/submissions.service';
import {AuthService} from '../../../login/auth.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-abstract-dialog',
  templateUrl: './edit-abstract-dialog.component.html',
  styleUrls: ['./edit-abstract-dialog.component.css']
})
export class EditAbstractDialogComponent implements OnInit {
  user: User = {} as User;
  submission: Submission = {
    id: null,
    title: null,
    abstractPaper: null,
    fullPaper: null,
    topics: null,
    keywords: null,
    finalVerdict: null,
    section: null,
    authors: [] = [],
    likes: null,
    reviews: null,
  };
  private selectedFile: File = null;
  showSpinner = false;


  constructor(
    public dialogRef: MatDialogRef<EditAbstractDialogComponent>,
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) data,
  ) {
    this.submission = {...data.submission}; // spread operator, like deep copy
  }

  ngOnInit(): void {
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
  }

  onUpload() {
    const fd = new FormData();
    fd.append('file', this.selectedFile, this.selectedFile.name);

    return this.submissionsService.uploadFile(fd);
  }

  onCancelClick() {
    this.dialogRef.close();
  }

  private validateInputAbstract() {
    return !(this.selectedFile === null ||
      this.submission.title === null ||
      this.submission.keywords === null ||
      this.submission.topics === null);
  }

  onEditClick() {
    if (!this.validateInputAbstract()) {
      this.snackBar.open('Fill all required fields!', '', {
        duration: 2000,
        panelClass: ['warning'],
      });
      return;
    }
    const conferenceId = this.authService.conference.id;
    this.showSpinner = true;

    this.onUpload().subscribe({
      next: (response: Submission) => {
        this.submission.abstractPaper = response.id;
        this.submissionsService.editAbstract(conferenceId, this.submission).subscribe({
          next: (responseSub: Submission) => {
            this.snackBar.open('Abstract paper edited.', 'Ok', {
              duration: 1000
            });
            this.showSpinner = false;
            this.dialogRef.close(responseSub);
          },
          error: err => {
            console.error('Error! ' + err);
            alert('Error occurred while editing.');
            this.showSpinner = false;
          }
        });
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Error occurred while uploading file.');
        this.showSpinner = false;
      }
    });
  }
}
