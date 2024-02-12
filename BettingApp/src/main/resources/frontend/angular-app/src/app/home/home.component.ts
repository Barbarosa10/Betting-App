import { Component } from '@angular/core';
import {AuthService} from "../auth.service";
import {TestService} from "../test/test.service";
import {ChatService} from "../chat-window/chat.service";
import {BiletService} from "../bilet/bilet.service";
import {Router} from "@angular/router";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {ChatWindowComponent} from "../chat-window/chat-window.component";
import {BiletComponent} from "../bilet/bilet.component";
import {CommonModule} from "@angular/common";
const materialComponentsModules= [
  MatToolbarModule,
  MatButtonModule,
  MatIconModule,
  MatCardModule,
  MatListModule,
  MatInputModule
]
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,materialComponentsModules, ChatWindowComponent,BiletComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(
    private testService: TestService,
    private chatService: ChatService,
    private biletService: BiletService,

  ) {}
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
}
