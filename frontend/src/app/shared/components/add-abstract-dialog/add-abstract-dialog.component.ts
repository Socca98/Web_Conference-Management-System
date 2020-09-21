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
  showSpinner = false;

  constructor(
    public dialogRef: MatDialogRef<AddAbstractDialogComponent>,
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
    const user = this.authService.user;
    this.submission.authors.push(user);
  }

  ngOnInit(): void {
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
  }

  onUpload() {
    const fd = new FormData();
    console.log(fd);
    fd.append('file', this.selectedFile, this.selectedFile.name);

    return this.submissionsService.uploadFile(fd);
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
    if (!this.validateInputAbstract()) {
      this.snackBar.open('Fill all required fields!', '', {
        duration: 2000,
        panelClass: ['warning'],
      });
      return;
    }
    const conferenceId = this.authService.conference.id;
    this.showSpinner = true;

    // upload file
    this.onUpload().subscribe({
      next: (response: Submission) => {
        this.submission.abstractPaper = response.id;
        this.submissionsService.addAbstract(conferenceId, this.submission).subscribe({
          next: (responseSub: Submission) => {
            this.dialogRef.close();
            this.snackBar.open('Abstract paper submitted.', 'Ok', {
              duration: 1000
            });
            // I have to copy this line everywhere since finally or onComplete don't work
            this.showSpinner = false;
          },
          error: err => {
            console.error('Error! ' + err);
            alert('Error occurred while submitting.');
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

  private validateInputAbstract() {
    return !(this.selectedFile === null ||
      this.submission.title === null ||
      this.submission.authors === null ||
      this.submission.keywords === null ||
      this.submission.topics === null);
  }

  /**
   * Close abstract dialog.
   */
  onCancelClick() {
    this.dialogRef.close();
  }

}
