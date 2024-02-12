export class UpdateTicketModel {
  public ticketId: string;
  public newStatus: string;

  constructor(ticketId: string, newStatus: string) {
    this.ticketId = ticketId;
    this.newStatus = newStatus;
  }
}
