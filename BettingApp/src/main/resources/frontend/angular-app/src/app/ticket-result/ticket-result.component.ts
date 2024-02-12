import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {UpdateTicketModel} from "./model/UpdateTicketModel";

@Component({
  selector: 'app-ticket-result',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './ticket-result.component.html',
  styleUrl: './ticket-result.component.css'
})
export class TicketResultComponent {
  ticketForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.ticketForm = this.fb.group({
      ticketId: ''
    });
  }

  updateStatus(status: string) {
    const apiUrl = 'http://localhost:8080/api/ticket/update';
    const updateTicketModel = new UpdateTicketModel(
      this.ticketForm.value.ticketId,
      status
    )

    this.http.post(apiUrl, updateTicketModel).subscribe(
      response => console.log('Success!', response),
      error => console.error('Error!', error)
    );
    this.ticketForm.reset()
  }
}
