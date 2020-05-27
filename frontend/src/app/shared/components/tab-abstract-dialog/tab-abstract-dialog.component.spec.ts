import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabAbstractDialogComponent } from './tab-abstract-dialog.component';

describe('TabAbstractDialogComponent', () => {
  let component: TabAbstractDialogComponent;
  let fixture: ComponentFixture<TabAbstractDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabAbstractDialogComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabAbstractDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
