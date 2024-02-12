import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TvBroadcastService {

  private baseUrl = 'http://127.0.0.1:8080/api/tv'; // Replace with your server's URL

  constructor(private http: HttpClient) { }

  getBroadcastUrl(channel: string) {
    return this.http.get<string>(`${this.baseUrl}/${channel}`, { responseType: 'text' as 'json' });
  }
}
