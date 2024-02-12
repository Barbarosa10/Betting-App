import {SelectedMatchBonus} from "./SelectedMatchBonus";

export class TicketRequestModel {
  userUid: string;
  matches: SelectedMatchBonus[];
  bet: number;
  potentialWin: number;

  constructor(userUid: string, matches: SelectedMatchBonus[], bet: number, potentialWin: number) {
    this.userUid = userUid;
    this.matches = matches;
    this.bet = bet;
    this.potentialWin = potentialWin;
  }
}
