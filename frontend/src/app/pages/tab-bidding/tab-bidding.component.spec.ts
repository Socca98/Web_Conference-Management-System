import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabBiddingComponent } from './tab-bidding.component';

describe('TabBiddingComponent', () => {
  let component: TabBiddingComponent;
  let fixture: ComponentFixture<TabBiddingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabBiddingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabBiddingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
