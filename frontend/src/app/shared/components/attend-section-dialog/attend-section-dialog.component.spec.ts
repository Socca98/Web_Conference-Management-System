import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendSectionDialogComponent } from './attend-section-dialog.component';

describe('AttendSectionDialogComponent', () => {
  let component: AttendSectionDialogComponent;
  let fixture: ComponentFixture<AttendSectionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendSectionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendSectionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
