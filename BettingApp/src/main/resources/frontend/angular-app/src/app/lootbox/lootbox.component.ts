import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-lootbox',
  standalone: true,
  imports: [],
  templateUrl: './lootbox.component.html',
  styleUrl: './lootbox.component.css'
})
export class LootboxComponent implements OnInit{
  constructor(private httpClient: HttpClient,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

  }

  openLootBox(boxNumber: number) {
      const body = {
          price: boxNumber,
          currency: "$",
          redirectUrl: window.location.href
      }
      const url = 'http://127.0.0.1:8080/api/users/pay';
      return this.httpClient.post(url, body)
          .subscribe(
              (response: any) => {
                  console.log('Response:', response);
                  window.location.href = response.redirect_url;
              },
              (error) => {
                  console.error('Error:', error);
              }
          );
  }
}
