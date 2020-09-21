import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../login/auth.service';
import {Role} from '../../shared/models/role';
import {MatDialog} from '@angular/material/dialog';
import {AddSectionDialogComponent} from '../../shared/components/add-section-dialog/add-section-dialog.component';
import {Section} from '../../shared/models/section';
import {ConferencesService} from '../../shared/services/conferences.service';
import {AttendSectionDialogComponent} from '../../shared/components/attend-section-dialog/attend-section-dialog.component';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-tab-sections',
  templateUrl: './tab-sections.component.html',
  styleUrls: ['./tab-sections.component.css']
})
export class TabSectionsComponent implements OnInit {
  sections: Section[] = [];
  isPayed = false;  // This should have been a field in the database. Otherwise, upon Logout you must pay again.
  isBlurredClass = 'blurred';

  constructor(
    private authService: AuthService,
    private conferenceService: ConferencesService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {
    // If role is Chair or Co-Chair, all the content must be shown without blur
    const userRole = this.authService.getUserRole();
    if (this.authService.user.payedAttend || userRole === Role.Chair || userRole === Role.CoChair) {
      this.activatePage();
    }
  }

  ngOnInit(): void {
    this.conferenceService.getSections(this.authService.conference.id).subscribe((result) => {
      this.sections = result;
      console.log(this.sections);
    });
    this.dialog.open(AddSectionDialogComponent);

  }

  /**
   * Eliminate blur class from background div element. [ngClass]=''
   */
  activatePage() {
    this.isPayed = true;
    this.isBlurredClass = '';
    const tempUser = this.authService.user;
    tempUser.payedAttend = true;
    this.authService.user = tempUser;
  }

  /**
   * Checks if role is Chair.
   * I cannot write Role.Chair in HTMl, so I made this function.
   */
  isChair() {
    return this.authService.getUserRole() === Role.Chair;
  }

  /**
   * Open add dialog to add a new section.
   */
  openAddSection() {
    this.dialog.open(AddSectionDialogComponent);
  }

  /**
   * Open dialog from where you pay.
   */
  openAttendSection() {
    this.dialog.open(AttendSectionDialogComponent).afterClosed()
      .subscribe((isPayed: boolean) => {
        if (isPayed) {
          // Allow user to use this tab-sections
          this.activatePage();
        }
      });
  }

  /**
   * Checks if you clicked Participate at the corresponding index section.
   * @param index Corresponding index section.
   */
  isCurrentUserListener(index) {
    const currentUser = this.authService.user;
    return !!this.sections[index].listeners.some(user => user.email === currentUser.email);
  }

  /**
   * By participating we decrement the number of seats in the database for a specific section.
   * @param sectionId Corresponds to a section tab.
   * @param index Identifies the section tab.
   */
  attendButton(sectionId, index) {
    const conferenceId = this.authService.conference.id;
    this.conferenceService.attendSection(conferenceId, sectionId).subscribe({
      next: () => {
        this.snackBar.open('Thank you for participating!', ':D', {
          duration: 1000,
        });
        this.sections[index].seats--; // We do not return the section, so we fake an update
      },
      error: () => {
        this.snackBar.open('Error attending section! Maybe you attended already.', 'Ok', {
          duration: 2000,
          panelClass: ['warning']
        });
      }
    });
  }
}
