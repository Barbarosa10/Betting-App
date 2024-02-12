// authentication.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.hasToken());
  isAuthenticated$: Observable<boolean> = this.isAuthenticatedSubject.asObservable();
  showLogin = true;
  showRegister = false;
  showChangePassword = false;
  constructor(private cookieService: CookieService) {}

  // Call this method when the user logs in
  login() {
    this.isAuthenticatedSubject.next(true);
  }

  // Call this method when the user logs out
  logout() {
    this.cookieService.delete("jwt");
    this.isAuthenticatedSubject.next(false);
  }

  // Check if the user is authenticated
  isAuthenticated(): boolean {
    return this.isAuthenticatedSubject.value;
  }

  private hasToken(): boolean {
    const token = this.cookieService.get('jwt'); // Adjust the storage location as needed
    return !!token;
  }

  public getUsernameFromToken() {
    if(!this.hasToken()) return "anonymous";
    const token = this.cookieService.get('jwt');
    let decodedJWT = JSON.parse(window.atob(token.split('.')[1]));
    console.log("username from token: "+ decodedJWT.username);
    return decodedJWT.username;
  }

  public getUserIDFromToken() {
    if(!this.hasToken()) return "anonymous";
    const token = this.cookieService.get('jwt');
    let decodedJWT = JSON.parse(window.atob(token.split('.')[1]));
    console.log("id from token: "+ decodedJWT.id);
    return decodedJWT.id;
  }


  showLoginForm() {
    this.showLogin = true;
    this.showRegister = false;
    this.showChangePassword = false;
  }

  showRegisterForm() {
    this.showLogin = false;
    this.showRegister = true;
    this.showChangePassword = false;
  }

  showChangePasswordForm() {
    this.showLogin = false;
    this.showRegister = false;
    this.showChangePassword = true;
  }
}
