import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketResultComponent } from './ticket-result.component';

describe('TicketResultComponent', () => {
  let component: TicketResultComponent;
  let fixture: ComponentFixture<TicketResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
