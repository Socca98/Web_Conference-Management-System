import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabAssignPapersComponent } from './tab-assign-papers.component';

describe('TabAssignPapersComponent', () => {
  let component: TabAssignPapersComponent;
  let fixture: ComponentFixture<TabAssignPapersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabAssignPapersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabAssignPapersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
