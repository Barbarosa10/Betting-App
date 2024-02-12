import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TvBroadcastComponent } from './tv-broadcast.component';

describe('TvBroadcastComponent', () => {
  let component: TvBroadcastComponent;
  let fixture: ComponentFixture<TvBroadcastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TvBroadcastComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TvBroadcastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
