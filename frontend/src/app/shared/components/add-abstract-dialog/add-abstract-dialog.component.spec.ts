import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAbstractDialogComponent } from './add-abstract-dialog.component';

describe('TabAbstractDialogComponent', () => {
  let component: AddAbstractDialogComponent;
  let fixture: ComponentFixture<AddAbstractDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAbstractDialogComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAbstractDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
