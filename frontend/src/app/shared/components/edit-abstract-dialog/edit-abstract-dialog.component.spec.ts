import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAbstractDialogComponent } from './edit-abstract-dialog.component';

describe('EditAbstractDialogComponent', () => {
  let component: EditAbstractDialogComponent;
  let fixture: ComponentFixture<EditAbstractDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditAbstractDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAbstractDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
