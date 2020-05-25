import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSectionDialogComponent } from './add-section-dialog.component';

describe('AddSectionDialogComponent', () => {
  let component: AddSectionDialogComponent;
  let fixture: ComponentFixture<AddSectionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSectionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSectionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
