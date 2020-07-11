import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {AddAbstractDialogComponent} from '../../shared/components/add-abstract-dialog/add-abstract-dialog.component';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-submission',
  templateUrl: './tab-submissions.component.html',
  styleUrls: ['./tab-submissions.component.css'],
  providers: [
    SubmissionsService,
  ]
})

export class TabSubmissionsComponent implements OnInit {
  submissions: Submission[];
  private selectedFile: File = null;
  private index: number;

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {

      const currentUser = this.authService.user;
      this.submissions = result.filter(
        s => s.authors.some(user => user.email === currentUser.email)
      );
    });
  }

  openAddAbstractDialog() {
    this.dialog.open(AddAbstractDialogComponent);
  }

  downloadAbstractPaper(i) {
    this.submissionsService.downloadFile(this.submissions[i].abstractPaper);
  }

  downloadFullPaper(i: number) {
    this.submissionsService.downloadFile(this.submissions[i].fullPaper);
  }

  openInput(i) {
    // your can use ElementRef for this later
    document.getElementById('fileInput').click();
    this.index = i;
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
    this.mainUploadFullPaper(this.index);
  }

  onUpload() {
    const fd = new FormData();
    console.log(fd);
    fd.append('file', this.selectedFile, this.selectedFile.name);

    return this.submissionsService.uploadFile(fd);
  }

  mainUploadFullPaper(i) {
    const conferenceId = this.authService.conference.id;

    // upload file
    this.onUpload().subscribe({
      next: (response: Submission) => {
        console.log('File uploaded successfully.');
        console.log(response);
        this.submissions[i].fullPaper = response.id;

        this.submissionsService.addFullPaper(conferenceId, this.submissions[i]).subscribe({
          next: (responseSub: Submission) => {
            alert(responseSub);
            this.snackBar.open('Full paper submitted.', 'Ok', {
              duration: 1000
            });
          },
          error: err => {
            console.error('Error! ' + err);
            alert('Error occurred while updating submission.');
          }
        });
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Error occurred while uploading file.');
      }
    });
  }
}
