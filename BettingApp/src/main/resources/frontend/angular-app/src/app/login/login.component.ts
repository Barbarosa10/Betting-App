import { Component, Output, EventEmitter } from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {CommonModule, NgForOf} from "@angular/common";
import { FormsModule } from '@angular/forms';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationRequest} from "../../../protos/identity";
import { CookieService } from 'ngx-cookie-service';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {FundsService} from "../funds.service";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatListModule,
    MatInputModule,
    NgForOf,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService,
    private authService: AuthService,
    private fundsService: FundsService,
    private router: Router
  ) { }

  @Output() showRegister = new EventEmitter<void>();
  @Output() showChangePassword = new EventEmitter<void>();

  username: string = '';
  password: string = '';
  isErrorMessageVisible :boolean = false;
  onLogin() {
    console.log('Login button clicked');
    console.log('Username:', this.username);
    console.log('Password:', this.password);
    const url = 'http://127.0.0.1:8080/api/users/authenticate';  // Replace with your actual API endpoint


    let credentials : AuthenticationRequest = {
      username: this.username,
      password: this.password
    };
    let bytes = AuthenticationRequest.toBinary(credentials);

    // Set the headers to specify the content type as 'application/octet-stream'
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-protobuf'
    });

    const blob = new Blob([bytes], { type: 'application/x-protobuf' });

    const options = {
      headers,
      responseType: 'text' as 'json',
    };
    this.httpClient.post<any>(url, blob, options)
      .subscribe(
        (response: any) => {
          console.log('Response:', response);

          this.fundsService.funds$ = JSON.parse(response).funds;
          this.cookieService.set('jwt', JSON.parse(response).token);
          this.authService.login();

          this.router.navigate([""]);
          console.log(this.authService.isAuthenticated());
        },
        (error) => {
          console.error('Error:', error);
        }
      );
  }
}
