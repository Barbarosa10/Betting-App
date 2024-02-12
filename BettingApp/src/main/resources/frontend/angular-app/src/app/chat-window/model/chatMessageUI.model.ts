export class ChatMessageUIModel {

  username: string | undefined;
  messageBody: string| undefined;
  constructor(username: string, messageBody: string) {
    this.username = username;
    this.messageBody = messageBody;
  }
}
