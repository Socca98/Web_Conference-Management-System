import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {AddAbstractDialogComponent} from '../../shared/components/add-abstract-dialog/add-abstract-dialog.component';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {EditAbstractDialogComponent} from '../../shared/components/edit-abstract-dialog/edit-abstract-dialog.component';


@Component({
  selector: 'app-submission',
  templateUrl: './tab-submissions.component.html',
  styleUrls: ['./tab-submissions.component.css'],
  providers: [
    SubmissionsService,
  ]
})

export class TabSubmissionsComponent implements OnInit {
  submissions: Submission[] = [];
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
      // Filter only submissions that belong to the current user
      // this.submissions = result;
      this.submissions = result.filter(
        s => s.authors.some(user => user.email === currentUser.email)
      );
    });
  }

  openAddAbstractDialog() {
    this.dialog.open(AddAbstractDialogComponent).afterClosed().subscribe((result: Submission) => {
      if (result) {
        this.submissions.push(result);
      }
    });
  }

  openEditAbstractDialog(submission: Submission, index) {
    // Send data to auto-populate edit dialog
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      submission
    };

    this.dialog.open(EditAbstractDialogComponent, dialogConfig).afterClosed()
      .subscribe((result: Submission) => {
        if (result) {
          this.submissions[index] = result;
        }
      });
  }

  downloadAbstractPaper(i) {
    this.submissionsService.downloadFile(this.submissions[i].abstractPaper);
  }

  downloadFullPaper(i: number) {
    this.submissionsService.downloadFile(this.submissions[i].fullPaper);
  }

  openInput(i) {
    // you can use ElementRef for this later
    document.getElementById('fileInput').click();
    this.index = i;
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
    this.mainUploadFullPaper(this.index);
  }

  /**
   * Attach full paper sending request.
   */
  onUpload() {
    const fd = new FormData();
    fd.append('file', this.selectedFile, this.selectedFile.name);
    return this.submissionsService.uploadFile(fd);
  }

  /**
   * Attach full paper
   * @param i index of the submission
   */
  mainUploadFullPaper(i) {
    const conferenceId = this.authService.conference.id;

    // Upload file
    this.onUpload().subscribe({
      next: (response: Submission) => {
        this.submissions[i].fullPaper = response.id;

        this.submissionsService.addFullPaper(conferenceId, this.submissions[i]).subscribe({
          next: (responseSub: Submission) => {
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
        alert('Error occurred while uploading file on server.');
      }
    });
  }

}
