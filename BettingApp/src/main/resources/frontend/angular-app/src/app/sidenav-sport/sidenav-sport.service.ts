import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { BehaviorSubject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SideNavService {

  constructor(private http: HttpClient) { }
  public sports: string[] = []
  public selectedSport: number=-1;
  public selectedSportName: string ="";
  public nrOfSports: number = 5;
  private _nrOfMatches = new BehaviorSubject<number[]>([]);
  public nrOfMatches$ = this._nrOfMatches.asObservable();

  setNrOfMatches(nrOfMatches: number[]) {
    this._nrOfMatches.next(nrOfMatches);
  }
}
