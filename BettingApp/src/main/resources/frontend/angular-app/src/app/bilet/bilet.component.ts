import {Component, OnInit} from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import {BiletService} from "./bilet.service";
import {CommonModule, NgForOf} from "@angular/common";
import {SelectedMatchService} from "../sport/selectedMatch.service";
import {SelectedMatch} from "./models/SelectedMatch";
import {Observable, Subscription} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {AuthService} from "../auth.service";
import {HttpClient} from "@angular/common/http";
import {TicketRequestModel} from "./models/TicketRequestModel";
import {SelectedMatchBonus} from "../sport/models/SelectedMatchBonus";
import {FormsModule} from "@angular/forms";
import {FundsService} from "../funds.service";
@Component({
  selector: 'app-bilet',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatListModule,
    MatInputModule,
    NgForOf,
    FormsModule,
    CommonModule],
  templateUrl: './bilet.component.html',
  styleUrl: './bilet.component.css'
})
export class BiletComponent{
  ceva: SelectedMatchBonus | null = null;
  selectedMatchList: SelectedMatchBonus[] = [];
  ticket: TicketRequestModel | null = null;
  odds: number = 1;
  miza: number = 100;
  funds: number | undefined = 1;
  totalAmount: number = 100;
  private subscription: Subscription;
  private apiUrl: string = "http://localhost:8080/api/ticket"
  constructor(private biletService: BiletService, private selectedMatchService: SelectedMatchService, private authService:AuthService,
              private client: HttpClient, protected fundsService: FundsService) {
    this.subscription = this.selectedMatchService.selectedMatch$.subscribe((match) => {
      this.ceva = match;
      if (match instanceof SelectedMatchBonus) {
        this.addSelectedMatch(match);
      }
      this.totalAmount = this.odds * this.miza;
    });

  }
  async method(){
    this.funds = await this.fundsService.funds$.toPromise();
  }
  addSelectedMatch(newMatch: SelectedMatchBonus): void {
    const existingMatch = this.selectedMatchList.find((match) => match.id === newMatch.id);

    if (existingMatch) {
      existingMatch.selectedOption = newMatch.selectedOption;
      existingMatch.odds = newMatch.odds;
    } else {
      this.selectedMatchList.push(newMatch);
    }
    this.odds = 1;
    this.selectedMatchList.forEach(match => this.odds *= match.odds);
  }
  closeChatWindow(): void {
    this.biletService.closeBiletWindow();
  }

  createTicketRequest(): void {
    const userUid = this.authService.getUserIDFromToken()
    var finalMatchList: SelectedMatch[] = [];
    var potentialWin = this.miza;
    this.selectedMatchList.forEach(match => {
      potentialWin *= match.odds;
      finalMatchList.push(new SelectedMatch(match.id, match.selectedOption))
    })
    this.ticket = new TicketRequestModel(userUid, this.selectedMatchList, this.miza, potentialWin);
  }

  postTicket(): void{
    if(this.selectedMatchList.length > 0) {
      this.createTicketRequest()
      this.client.post(this.apiUrl, this.ticket).subscribe(
        response => console.log('Success!', response),
        error => console.error('Error!', error)
      );
      this.clearList()
    }
  }
  clearList(): void{
    this.odds = 1;
    this.miza = 100;
    this.selectedMatchList = []
  }
}
