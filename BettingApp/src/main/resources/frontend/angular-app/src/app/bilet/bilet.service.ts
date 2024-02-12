import { Injectable } from '@angular/core';
import {baseUrl} from "../apiUrl";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BiletService {

  private isChatWindowVisible: boolean = false;
  constructor(private http: HttpClient) { }

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
