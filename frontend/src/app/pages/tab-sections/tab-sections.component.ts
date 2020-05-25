import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../login/auth.service';
import {Role} from '../../shared/models/role';
import {MatDialog} from '@angular/material/dialog';
import {AddSectionDialogComponent} from '../../shared/components/add-section-dialog/add-section-dialog.component';
import {Section} from '../../shared/models/section';
import {ConferencesService} from '../../shared/services/conferences.service';
import {Review} from '../../shared/models/review';

@Component({
  selector: 'app-tab-sections',
  templateUrl: './tab-sections.component.html',
  styleUrls: ['./tab-sections.component.css']
})
export class TabSectionsComponent implements OnInit {
  sections: Section[] = [];

  constructor(
    private authService: AuthService,
    private conferenceService: ConferencesService,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.conferenceService.getSections(this.authService.conference.id).subscribe((result: Section[]) => {
      this.sections = result;
    });
  }

  /**
   * Checks if role is Chair.
   * I cannot write Role.Chair in HTMl, so I made this function.
   */
  isChair() {
    return this.authService.getUserRole() === Role.Chair;
  }

  openAddSection() {
    this.dialog.open(AddSectionDialogComponent);
  }
}
