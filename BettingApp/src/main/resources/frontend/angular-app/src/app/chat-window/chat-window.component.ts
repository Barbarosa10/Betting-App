import {AfterViewChecked, Component, ElementRef, ViewChild} from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {ChatService} from "./chat.service";
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {ChatMessageUIModel} from "./model/chatMessageUI.model";

@Component({
  selector: 'app-chat-window',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
    NgForOf
  ],
  templateUrl: './chat-window.component.html',
  styleUrl: './chat-window.component.css'
})
export class ChatWindowComponent implements AfterViewChecked{
  messageControl = new FormControl('');
  @ViewChild('messageArea') private messageArea!: ElementRef;
  constructor(
    private chatService: ChatService
  ) {
  }

  closeChatWindow(): void {
    this.chatService.closeChatWindow();
  }

  sendMessage(): void {
    const message = this.messageControl.value || "";
    this.clearMessageInput();
    if (message?.trim() == "") return;

    this.chatService.sendMessage(message);
  }

  clearMessageInput(): void {
    this.messageControl.setValue("");
  }

  get messageList():  Array<ChatMessageUIModel> {
    return this.chatService.getMessages();
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  private scrollToBottom(): void {
    if (this.isUserNearBottom()) {
      console.log("scrollToBottom scrollTop to: " + this.messageArea.nativeElement.scrollHeight)
      this.messageArea.nativeElement.scrollTop = this.messageArea.nativeElement.scrollHeight;
    }
  }
  private isUserNearBottom(): boolean {
    const threshold = 150;
    const position = this.messageArea.nativeElement.scrollTop + this.messageArea.nativeElement.offsetHeight;
    const height = this.messageArea.nativeElement.scrollHeight;
    return position > height - threshold;
  }
}
