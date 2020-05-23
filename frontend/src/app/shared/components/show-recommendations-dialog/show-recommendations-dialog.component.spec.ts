import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowRecommendationsDialogComponent } from './show-recommendations-dialog.component';

describe('ShowRecommendationsDialogComponent', () => {
  let component: ShowRecommendationsDialogComponent;
  let fixture: ComponentFixture<ShowRecommendationsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowRecommendationsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowRecommendationsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
