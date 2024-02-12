import {SelectedMatch} from "./SelectedMatch";

export class TicketRequestModel {
  userUid: string;
  matches: SelectedMatch[];
  bet: number;
  potentialWin: number;

  constructor(userUid: string, matches: SelectedMatch[], bet: number, potentialWin: number) {
    this.userUid = userUid;
    this.matches = matches;
    this.bet = bet;
    this.potentialWin = potentialWin;
  }
}
