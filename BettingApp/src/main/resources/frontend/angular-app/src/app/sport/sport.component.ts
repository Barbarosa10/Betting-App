import {Component, ElementRef, OnInit} from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {CommonModule, NgForOf} from "@angular/common";
import {MatchModel} from "./models/MatchModel";
import {SportService} from "./sport.service";
import {BiletService} from "../bilet/bilet.service";
import {ChatService} from "../chat-window/chat.service";
import {ChatWindowComponent} from "../chat-window/chat-window.component";
import {BiletComponent} from "../bilet/bilet.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {FormsModule} from '@angular/forms';
import {CheckBoxService} from "../check-box-list/chat-box-list.service";
import {CheckBoxComponent} from "../check-box-list/check-box-list.component";
import {SidenavSportComponent} from "../sidenav-sport/sidenav-sport.component";
import {SideNavService} from "../sidenav-sport/sidenav-sport.service";
import {MatFormFieldModule} from "@angular/material/form-field";
import {TicketRequestModel} from "./models/TicketRequestModel";
import {SelectedMatchBonus} from "./models/SelectedMatchBonus";
import {SelectedMatchService} from "./selectedMatch.service";

const materialComponentsModules= [
  MatToolbarModule,
  MatButtonModule,
  MatIconModule,
  MatCardModule,
  MatListModule,
  MatInputModule
]

@Component({
  selector: 'app-sport',
  standalone: true,
  imports: [
    NgForOf,
    CommonModule,materialComponentsModules, ChatWindowComponent,BiletComponent, FormsModule, CheckBoxComponent, SidenavSportComponent,
  MatFormFieldModule],
  templateUrl: './sport.component.html',
  styleUrls: ['./sport.component.css']
})
export class SportComponent implements OnInit{
  public matches: MatchModel[] = [];
  selectedMatches: SelectedMatchBonus[] = [];
  public searchTerm: string = '';
  public filteredMatches: MatchModel[] = [];
  public filteredMatchesCB: MatchModel[] = [];
  constructor(private sportService: SportService,private chatService: ChatService,
              private biletService: BiletService, private checkboxService: CheckBoxService, private elementRef: ElementRef,
              private  sidenav: SideNavService, private selectedMatchService: SelectedMatchService) {}
  ngOnInit(): void {

    this.sportService.getMatches().subscribe(data => {
      this.matches = data;
      this.matches.forEach(match => this.setOddsToDefault(match));
    });
  }
  addSelectedMatch(match: MatchModel, selectedOption: string): void {
    const selectedMatch = new SelectedMatchBonus(match.id, selectedOption, match.odds_generated.odds[selectedOption], match.homeTeam.name, match.awayTeam.name);
    this.selectedMatchService.setSelectedMatch(selectedMatch);
  }

  setOddsToDefault(match: MatchModel): void {
    if (!match.odds_generated) {
      match.odds_generated = { odds: {} };
    }

    const oddsKeys = ['1', 'X', '2'];

    oddsKeys.forEach((key) => {
      if (match.odds_generated.odds[key] === null || match.odds_generated.odds[key] === undefined) {
        match.odds_generated.odds[key] = 1;
      }
    });
  }
  openChatWindow(): void {
    this.chatService.openChatWindow();
  }
  openBiletWindow(): void {
    this.biletService.openBiletWindow();
  }
  get isChatVisible(): boolean {
    return this.chatService.getChatWindowVisibility();
  }
  get isBiletVisible(): boolean {
    return this.biletService.getBiletWindowVisibility();
  }
  filterMatchesCheckBox() {
    if (this.checkboxService.showOlderMatches) {
      this.filteredMatches = this.matches;
      this.filterMatches();
    } else {
      this.filteredMatches =  this.filteredMatches.filter((match) => this.isAfterToday(match.utcDate));
    }

  }
  formatDate(utcDateString: string): string {
    const currentDate = new Date();
    const matchDate = new Date(utcDateString);

    const isToday = matchDate.toDateString() === currentDate.toDateString();
    const isTomorrow = matchDate.toDateString() === new Date(currentDate.getTime() + 86400000).toDateString();

    if (isToday) {
      return `Today, ${this.formatTime(matchDate)}`;
    } else if (isTomorrow) {
      return `Tomorrow, ${this.formatTime(matchDate)}`;
    } else {
      return `${this.formatFullDate(matchDate)}, ${this.formatTime(matchDate)}`;
    }
  }

  private formatFullDate(date: Date): string {
    const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'long', day: 'numeric' };
    return date.toLocaleDateString(undefined, options);
  }

  private formatTime(date: Date): string {
    const options: Intl.DateTimeFormatOptions = { hour: '2-digit', minute: '2-digit' };
    return date.toLocaleTimeString(undefined, options);
  }
  public isAfterToday(utcDateString: string): boolean {
    const currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);

    const matchDate = new Date(utcDateString);
    matchDate.setHours(0, 0, 0, 0);

    return matchDate >= currentDate;
  }
  public isAfterNow(utcDateString: string): boolean {
    const currentDate = new Date();
    const matchDate = new Date(utcDateString);

    return true;
    return matchDate >= currentDate;
  }
  getSortedMatches() {
    this.filterMatches();
    this.filterMatchesCheckBox();

    this.filteredMatches = this.sortMatches(this.filteredMatches, this.checkboxService.sortOption);
    this.setNoOfMatches();
  }

  setNoOfMatches(){
    var tempMatches;
    if (this.checkboxService.showOlderMatches) {
      tempMatches = this.matches;
      this.filterMatches();
    } else {
      tempMatches =  this.filteredMatches.filter((match) => this.isAfterToday(match.utcDate));
    }
    const searchTermLowerCase = this.searchTerm.toLowerCase();
    tempMatches = tempMatches.filter((match) =>
      match.area.name.toLowerCase().includes(searchTermLowerCase) ||
      match.competition.name.toLowerCase().includes(searchTermLowerCase) ||
      match.utcDate.includes(this.searchTerm) ||
      match.homeTeam.name.toLowerCase().includes(searchTermLowerCase) ||
      match.awayTeam.name.toLowerCase().includes(searchTermLowerCase)
    );
    var tempNrOfMatches : number[] = []
    for (let i = 0; i < this.sidenav.nrOfSports; i++) {
        tempNrOfMatches[i] = tempMatches.filter(match => match.sportType == this.sidenav.sports[i]).length;
    }
    this.sidenav.setNrOfMatches(tempNrOfMatches)
  }
  private sortMatches(matches: MatchModel[], sortOption: string): MatchModel[] {
    switch (sortOption) {
      case 'date_d':
        return matches.slice().sort((a, b) => new Date(b.utcDate).getTime() - new Date(a.utcDate).getTime());
      case 'date_a':
        return matches.slice().sort((a, b) => new Date(a.utcDate).getTime() - new Date(b.utcDate).getTime());
      case 'homeTeam':
        return matches.slice().sort((a, b) => a.homeTeam.name.localeCompare(b.homeTeam.name));
      case 'awayTeam':
        return matches.slice().sort((a, b) => a.awayTeam.name.localeCompare(b.awayTeam.name));
      default:
        return matches;
    }
  }
  filterMatches(): void {

    if(this.sidenav.selectedSport != -1){
      this.filteredMatches = this.matches.filter(match => match.sportType == this.sidenav.selectedSportName)
    }
    else
      this.filteredMatches = this.matches;

    const searchTermLowerCase = this.searchTerm.toLowerCase();
    this.filteredMatches = this.filteredMatches.filter((match) =>
      match.area.name.toLowerCase().includes(searchTermLowerCase) ||
      match.competition.name.toLowerCase().includes(searchTermLowerCase) ||
      match.utcDate.includes(this.searchTerm) ||
      match.homeTeam.name.toLowerCase().includes(searchTermLowerCase) ||
      match.awayTeam.name.toLowerCase().includes(searchTermLowerCase)
    );
  }
}
