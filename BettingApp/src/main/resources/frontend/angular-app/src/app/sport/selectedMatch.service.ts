import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {SelectedMatchBonus} from "./models/SelectedMatchBonus";

@Injectable({
  providedIn: 'root'
})
export class SelectedMatchService {
  private selectedMatchSource = new BehaviorSubject<SelectedMatchBonus | null>(null);
  selectedMatch$ = this.selectedMatchSource.asObservable();

  setSelectedMatch(selectedMatch: SelectedMatchBonus): void {
    this.selectedMatchSource.next(selectedMatch);
  }
}
