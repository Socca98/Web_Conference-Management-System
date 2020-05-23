import { Component, OnInit } from '@angular/core';
import {Submission} from '../../shared/models/submission';
import {SubmissionsService} from '../../shared/services/submissions.service';
import {User} from '../../shared/models/user';

interface Tab {
  submission: Submission;
  usersToAssign: User[];
}

@Component({
  selector: 'app-tab-assign-papers',
  templateUrl: './tab-assign-papers.component.html',
  styleUrls: ['./tab-assign-papers.component.css']
})
export class TabAssignPapersComponent implements OnInit {
  submissions: Submission[];
  users: User[];
  tabs: Tab[];
  selection: User;

  constructor(
    private submissionsService: SubmissionsService
  ) { }

  ngOnInit(): void {
    this.submissionsService.getSubmissions().subscribe((result: Submission[]) => {
      this.submissions = result;
    });
    // TODO: add users
    for (const submission of this.submissions) {
      this.tabs.push({submission, usersToAssign: []});
    }
  }

  addSelectionToTab(tab: Tab) {
    tab.usersToAssign.push(this.selection);
  }

  assign(tab: Tab) {
    const conferenceId = localStorage.getItem('conferenceId');
    if (conferenceId === null) {
      throw new Error('Error! Could not retrieve conference id!');
    }
    // TODO: make post request
  }
}
