import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FundsService {
  private fundsSubject: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  funds$: Observable<number> = this.fundsSubject.asObservable();
  constructor() { }

  setFunds(newFunds: number): void {
    this.fundsSubject.next(newFunds);
  }
}
