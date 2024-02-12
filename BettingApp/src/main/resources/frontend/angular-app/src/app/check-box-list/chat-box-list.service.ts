import { Injectable } from '@angular/core';
import {baseUrl} from "../apiUrl";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckBoxService {

  public showOlderMatches: boolean = false;
  public sortOption: string = 'date_d';
  constructor(private http: HttpClient) { }

  getShowOlderMatches(): boolean {
    return this.showOlderMatches;
  }
  getSortOption(): string {
    return this.sortOption;
  }
}
