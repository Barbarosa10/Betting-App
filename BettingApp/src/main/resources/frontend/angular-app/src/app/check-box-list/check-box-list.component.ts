import { Component } from '@angular/core';
import {CheckBoxService} from "./chat-box-list.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-check-box-list',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './check-box-list.component.html',
  styleUrl: './check-box-list.component.css'
})
export class CheckBoxComponent{

  constructor(public checkboxService: CheckBoxService) {}
}
