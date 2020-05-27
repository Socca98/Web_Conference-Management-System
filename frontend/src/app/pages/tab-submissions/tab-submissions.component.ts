import {Component, OnInit} from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {AuthService} from '../../login/auth.service';
import {TabAbstractDialogComponent} from '../../shared/components/tab-abstract-dialog/tab-abstract-dialog.component';
import {MatDialog} from '@angular/material/dialog';


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

  constructor(
    private submissionsService: SubmissionsService,
    private authService: AuthService,
    private dialog: MatDialog,
  ) {
  }

  ngOnInit(): void {
    this.submissionsService.getSubmissions(this.authService.conference.id).subscribe((result: Submission[]) => {
      this.submissions = result;
    });
  }

  openAddAbstractDialog() {
    this.dialog.open(TabAbstractDialogComponent);
  }
}
