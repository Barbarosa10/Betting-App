import { Component, Output, EventEmitter } from '@angular/core';
import {CommonModule, NgForOf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatListModule,
    MatInputModule,
    NgForOf,
    FormsModule],
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {
  constructor(private httpClient: HttpClient) { }

  @Output() showRegister = new EventEmitter<void>();
  @Output() showLogin = new EventEmitter<void>();

  username: string = '';
  oldPassword: string = '';
  newPassword: string = '';

  onChangePassword() {
    // Add your authentication logic here
    console.log('Change Password button clicked');
    console.log('Username:', this.username);
    console.log('OldPassword:', this.oldPassword);
    console.log('newPassword:', this.newPassword);

    const url = 'http://127.0.0.1:8080/api/users/changePassword';  // Replace with your actual API endpoint

    const user = {
      username: this.username,
      oldPassword: this.oldPassword,
      newPassword: this.newPassword
    }

    this.httpClient.post(url, user)
        .subscribe(
            (response) => {
              console.log('Response:', response);
            },
            (error) => {
              console.error('Error:', error);
            }
        );
  }
}
