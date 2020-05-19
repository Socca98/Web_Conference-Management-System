import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabReviewingComponent } from './tab-reviewing.component';

describe('TabReviewingComponent', () => {
  let component: TabReviewingComponent;
  let fixture: ComponentFixture<TabReviewingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabReviewingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabReviewingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
