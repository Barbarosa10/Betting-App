import { Component, Output, EventEmitter } from '@angular/core';
import {CommonModule, NgForOf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from "@angular/router";
import {AuthService} from "../auth.service";
@Component({
  selector: 'app-register',
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
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private httpClient: HttpClient) { }

  @Output() showLogin = new EventEmitter<void>();
  @Output() showChangePassword = new EventEmitter<void>();

  username: string = '';
  password: string = '';
  confirmpassword: string ='';
  CNP: string = '';
  isErrorMessageVisible :boolean = false;
  isAgeVerificationFailed:boolean=false;
  isRegisterOK:boolean=false;
  onRegister() {
    console.log('Register button clicked');
    console.log('Username:', this.username);
    console.log('Password:', this.password);

    const url = 'http://127.0.0.1:8080/api/users';  // Replace with your actual API endpoint
    const user = {
      username: this.username,
      password: this.password,
      cnp: this.CNP,
      role: 'utilizator'
    }
    if(this.password!=this.confirmpassword)
    {
      this.isErrorMessageVisible=true;
      return;
    }
    else {
      this.isErrorMessageVisible=false;
    }
    console.log('user:', user);
      if (!this.isAbove18(this.CNP)) {
        console.log('User is not above 18 or invalid CNP');
        this.isAgeVerificationFailed = true;
        return;
      }
      else {
        this.isAgeVerificationFailed = false;
      }
    this.httpClient.post(url, user)
        .subscribe(
            (response) => {
              console.log('Response:', response);
              this.isRegisterOK =true;
            },
            (error) => {
              console.error('Error:', error);
            }
        );
  }
  private isAbove18(cnp: string): boolean {
    if (cnp.length !== 13 || !cnp.match(/^\d+$/)) {
      return false; // Return false if CNP is invalid
    }

    let year = parseInt(cnp.substring(1, 3), 10);
    const month = parseInt(cnp.substring(3, 5), 10) - 1;
    const day = parseInt(cnp.substring(5, 7), 10);

    const firstDigit = parseInt(cnp.charAt(0), 10);
    if (firstDigit === 1 || firstDigit === 2) {
      year += 1900;
    } else if (firstDigit === 5 || firstDigit === 6) {
      year += 2000;
    } else {
      return false; // Return false if CNP format is not correct
    }

    const birthDate = new Date(year, month, day);
    const today = new Date();
    const age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();

    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      return age > 18;
    }

    return age >= 18;
  }
}
