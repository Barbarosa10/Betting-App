import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import {Injectable} from "@angular/core";
import {ChatMessageUIModel} from "../chat-window/model/chatMessageUI.model";
import {AuthService} from "../auth.service";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService{

  private stompClient: any;
  private maxMessages  = 10;
  messageList: Array<ChatMessageUIModel> = [];
  constructor(private authService: AuthService) {}

  connect() {
    let username = this.authService.getUsernameFromToken()
    if(username === null) return;
    const socket = new SockJS('http://localhost:8080/ws');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame:any)  => {

      this.stompClient.send(
          '/api/chat.addUser',
          {},
          JSON.stringify(new ChatMessageUIModel(username,""))
          );

      console.log("username: " + username);
      this.stompClient.subscribe("/topic/public", (message: any) => {
        const parsedMessage = JSON.parse(message.body) as ChatMessageUIModel;
        if(parsedMessage.messageBody) {
          console.log("Message: " + parsedMessage)
          this.updateMessageList(parsedMessage);
        }

      });
    });
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  sendMessage(messageBody: string) {
    let username = this.authService.getUsernameFromToken()
    const message = new ChatMessageUIModel(username, messageBody);
    this.stompClient.send("/api/chat.sendMessage", {}, JSON.stringify(message));
  }

  updateMessageList(message: ChatMessageUIModel) {
    if( this.messageList.length === this.maxMessages ) {
      this.messageList.shift();
    }
    this.messageList.push(message);
  }
}
