import {Component, OnInit} from '@angular/core';
import {Submission} from '../../models/submission';
import {User} from '../../models/user';
import {MatDialogRef} from '@angular/material/dialog';
import {SubmissionsService} from '../../services/submissions.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AuthService} from '../../../login/auth.service';

@Component({
  selector: 'app-tab-abstract-dialog',
  templateUrl: './add-abstract-dialog.component.html',
  styleUrls: ['./add-abstract-dialog.component.css']
})
export class AddAbstractDialogComponent implements OnInit {
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


  constructor(
    public dialogRef: MatDialogRef<AddAbstractDialogComponent>,
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
    const user = {} as User;
    this.submission.authors.push(user);
  }

  ngOnInit(): void {
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
    this.submission.abstractPaper = this.selectedFile.name;
  }

  onUpload() {
    const fd = new FormData();
    console.log(fd);
    fd.append('file', this.selectedFile, this.selectedFile.name);

    this.submissionsService.uploadFile(fd)
      .subscribe({
        next: (response: Submission) => {
          console.log(response);
          console.log('File uploaded successfully.');
        },
        error: err => {
          console.error('Error! ' + err);
          alert('Error occurred while uploading file.');
        }
      });
  }

  incrementNumberOfAuthors() {
    const user = {} as User;
    this.submission.authors.push(user);
  }

  identify(index, item) {
    return item.id;
  }

  /**
   * Sends 'this.submission to SubmissionsService for submit.
   * Submit button's click calls this function.
   * In HTML, [(ngModel)] changes automatically object 'this.submission' if input fields change.
   */
  onSubmitClick() {
    const conferenceId = this.authService.conference.id;

    // upload file
    this.onUpload();

    this.submissionsService.addAbstract(conferenceId, this.submission).subscribe({
      next: (response: Submission) => {
        alert(response);
        this.dialogRef.close();
        this.snackBar.open('Abstract paper submitted.', 'Ok', {
          duration: 1000
        });
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Error occurred while submitting.');
      }
    });
  }

  /**
   * Close abstract dialog.
   */
  onCancelClick() {
    this.dialogRef.close();
  }
}
