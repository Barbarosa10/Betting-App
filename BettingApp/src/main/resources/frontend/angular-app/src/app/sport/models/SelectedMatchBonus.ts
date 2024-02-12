export class SelectedMatchBonus {
  id: number;
  selectedOption: string;
  odds: number;
  homeTeam: string;
  awayTeam: string;
  constructor(id: number, selectedOption: string, odds: number, homeTeam: string, awayTeam: string) {
    this.id = id;
    this.selectedOption = selectedOption;
    this.odds = odds;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
  }
}
