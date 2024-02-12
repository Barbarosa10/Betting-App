import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { baseUrl} from "../apiUrl";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  //http://localhost:8080/api/test endpoint
  private apiUrl = baseUrl + 'matches';

  constructor(private http: HttpClient) { }

  getTestMessage(): Observable<any> {
    return this.http.get(this.apiUrl, {responseType: 'text'});
  }
}
