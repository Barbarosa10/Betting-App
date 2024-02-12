import {ChangeDetectionStrategy, Component} from '@angular/core';
import {NgFor} from "@angular/common";
import {SideNavService} from "./sidenav-sport.service";

@Component({
  selector: 'app-sidenav-sport',
  changeDetection: ChangeDetectionStrategy.OnPush,
  standalone: true,
  imports: [NgFor],
  templateUrl: './sidenav-sport.component.html',
  styleUrl: './sidenav-sport.component.css'
})
export class SidenavSportComponent {
  sports: string[] = ['Football', 'Basketball', 'Volleyball', 'Tennis', 'Rugby'];
  selectedSport: string = "";
  nrOfMatches: number[] = [];
  constructor(public sidenav: SideNavService) {
    this.sidenav.nrOfMatches$.subscribe((matches) => {
      this.nrOfMatches = matches;
    });
    sidenav.nrOfSports = this.sports.length;
  }

  selectSport(sport: string) {
    const index = this.sports.indexOf(sport);
    this.sidenav.sports = this.sports;
    if (index !== -1) {
      this.selectedSport = this.selectedSport === sport ? "" : sport;
      this.sidenav.selectedSportName = this.selectedSport;
      this.sidenav.selectedSport = this.selectedSport ? index + 1 : -1;
    } else {
      this.selectedSport = "";
      this.sidenav.selectedSportName = "";
      this.sidenav.selectedSport = -1;
    }
  }

}
