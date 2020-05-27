import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabSectionsComponent } from './tab-sections.component';

describe('TabSectionsComponent', () => {
  let component: TabSectionsComponent;
  let fixture: ComponentFixture<TabSectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabSectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabSectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
