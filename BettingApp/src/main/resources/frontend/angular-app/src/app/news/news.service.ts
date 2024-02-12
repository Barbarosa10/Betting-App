import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {NewsModel} from "./NewsModel";

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private baseUrl = 'http://127.0.0.1:8080/api/crawl'; // Adjust the URL as necessary

  constructor(private http: HttpClient) { }

  getNewsByCountry(country: string): Observable<any> {
    return this.http.get<NewsModel[]>(`${this.baseUrl}/${country}`);
  }
}
