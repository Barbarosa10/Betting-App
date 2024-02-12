import { Injectable } from '@angular/core';
import {baseUrl} from "../apiUrl";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MatchModel} from "./models/MatchModel";

@Injectable({
  providedIn: 'root'
})
export class SportService {

  //http://localhost:8080/api/matches endpoint
  private apiUrl = baseUrl + 'matches';
  private isChatWindowVisible: boolean = false;

  constructor(private http: HttpClient) {
  }

  getMatches(): Observable<MatchModel[]> {
    return this.http.get<MatchModel[]>(this.apiUrl);
  }

  openBiletWindow(): void {
    this.isChatWindowVisible = true;
    //this.webSocketService.connect()
  }

  closeBiletWindow(): void {
    this.isChatWindowVisible = false;
    // this.webSocketService.disconnect();
  }

  getBiletWindowVisibility(): boolean {
    return this.isChatWindowVisible;
  }
}
