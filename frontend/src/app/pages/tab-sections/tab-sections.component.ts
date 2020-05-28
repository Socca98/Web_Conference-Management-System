import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../login/auth.service';
import {Role} from '../../shared/models/role';
import {MatDialog} from '@angular/material/dialog';
import {AddSectionDialogComponent} from '../../shared/components/add-section-dialog/add-section-dialog.component';
import {Section} from '../../shared/models/section';
import {ConferencesService} from '../../shared/services/conferences.service';
import {AttendSectionDialogComponent} from '../../shared/components/attend-section-dialog/attend-section-dialog.component';
import {User} from '../../shared/models/user';
import {Conference} from '../../shared/models/conference';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-tab-sections',
  templateUrl: './tab-sections.component.html',
  styleUrls: ['./tab-sections.component.css']
})
export class TabSectionsComponent implements OnInit {
  sectionOne = {
    id: 1,
    title: 'Section One',
    sectionChair: {fullName: 'Ion Baciu'} as User,
    conference: {} as Conference,
    submissions: [] = [],
    startTime: '1588326800',
    endTime: '1590632553',
    speakers: [] as User[],
    listeners: [] as User[],
    seats: 3,
  };
  sections: Section[] = [
    this.sectionOne, this.sectionOne, this.sectionOne
  ];
  isPayed = false;
  isBlurredClass = 'blurred';

  constructor(
    private authService: AuthService,
    private conferenceService: ConferencesService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {
    // If role is Chair or Co-Chair, all the content must be shown
    const userRole = this.authService.getUserRole();
    if (this.authService.user.payedAttend || userRole === Role.Chair || userRole === Role.CoChair) {
      this.activatePage();
    }
    // this.activatePage();
  }

  ngOnInit(): void {
    this.conferenceService.getSections(this.authService.conference.id).subscribe((result: Section[]) => {
      // this.sections = result;
    });
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

  attendButton(sectionId) {
    const conferenceId = this.authService.conference.id;
    this.conferenceService.attendSection(conferenceId, sectionId).subscribe({
      next: () => {
        this.snackBar.open('Thank you for participating!', ':D', {
          duration: 1000,
        });
      },
      error: () => {
        this.snackBar.open('Error attending section!', 'Ok', {
          duration: 1000,
          panelClass: ['warning']
        });
      }
    });
  }
}
