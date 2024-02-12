import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavSportComponent } from './sidenav-sport.component';

describe('SidenavSportComponent', () => {
  let component: SidenavSportComponent;
  let fixture: ComponentFixture<SidenavSportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidenavSportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SidenavSportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
