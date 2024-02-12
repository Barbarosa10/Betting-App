import { Injectable } from '@angular/core';
import {WebSocketService} from "../webSocket/web-socket.service";
import {ChatMessageUIModel} from "./model/chatMessageUI.model";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private isChatWindowVisible: boolean = false;
  constructor(
    private webSocketService: WebSocketService
  ) { }

  openChatWindow(): void {
    this.isChatWindowVisible = true;
    this.webSocketService.connect()
  }
  closeChatWindow(): void {
    this.isChatWindowVisible = false;
    this.webSocketService.disconnect();
  }

  sendMessage(message: string) {
    this.webSocketService.sendMessage(message)
  }

  getChatWindowVisibility(): boolean {
    return this.isChatWindowVisible;
  }
  getMessages():  Array<ChatMessageUIModel> {
    return this.webSocketService.messageList;
  }
}
