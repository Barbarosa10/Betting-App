import { Component, OnInit } from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {CommonModule, NgForOf} from "@angular/common";
import { FormsModule } from '@angular/forms';
import {RegisterComponent} from "../register/register.component";
import {LoginComponent} from "../login/login.component";
import {ChangePasswordComponent} from "../change-password/change-password.component";
import {CookieService} from "ngx-cookie-service";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatListModule,
    MatInputModule,
    NgForOf,
    FormsModule,
    RegisterComponent,
    LoginComponent,
    ChangePasswordComponent
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {

  constructor(private authService: AuthService) {}
  // showLogin = true;
  // showRegister = false;
  // showChangePassword = false;

  showLogin() {
    return this.authService.showLogin;
  }
  showRegister() {
    return this.authService.showRegister;
  }
  showChangePassword() {
    return this.authService.showChangePassword;
  }

  showLoginForm() {
    this.authService.showLoginForm();
  }

  showRegisterForm() {
    this.authService.showRegisterForm();

  }

  showChangePasswordForm() {
    this.authService.showChangePasswordForm();

  }


}
