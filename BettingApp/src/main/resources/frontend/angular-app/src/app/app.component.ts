import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ActivatedRoute, RouterOutlet} from '@angular/router';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import {ChatWindowComponent} from "./chat-window/chat-window.component";
import {BiletComponent} from "./bilet/bilet.component";
import {AuthComponent} from "./auth/auth.component";
import {AuthService} from "./auth.service";
import {CookieService} from "ngx-cookie-service";
import { jwtDecode, JwtPayload } from "jwt-decode";

import { Router } from '@angular/router';
import {FundsService} from "./funds.service";
const materialComponentsModules= [
  MatToolbarModule,
  MatButtonModule,
  MatIconModule,
  MatCardModule,
  MatListModule,
  MatInputModule
]

interface CustomJwtPayload {
  id: string;
  username: string;
  iat: number;
  exp: number;
  // Add any other fields you have in your JWT payload
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, CommonModule, RouterOutlet, materialComponentsModules, ChatWindowComponent,BiletComponent, AuthComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{

  message = '???';
  title = 'angular-app';
  funds = 0.0;
  constructor(
    protected authService: AuthService,
    private httpClient: HttpClient,
    private route: ActivatedRoute,
    public router: Router,
    protected fundsService: FundsService,
    private cookieService: CookieService

  ) {}

   ngOnInit() {
     this.route.queryParams.subscribe(params => {
       console.log(params);
       const paymentId = params['paymentId'];
       const token = params['token'];
       const payerId = params['PayerID'];

       if (paymentId && token && payerId) {
         console.log(paymentId + " " + token + " " + payerId);
         const url = 'http://127.0.0.1:8080/api/users/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId;
         const cleanUrl = window.location.href.split('?')[0];
         history.replaceState({}, document.title, cleanUrl);
         this.httpClient.post(url, {})
           .subscribe(
             (response: any) => {
               console.log('Response:', response);
               this.fundsService.funds$ = response.funds
               // window.location.href = response.redirect_url;
             },
             (error) => {
               console.error('Error:', error);
             }
           );
       }
       else{
          let jwt: CustomJwtPayload = jwtDecode(this.cookieService.get('jwt'));
           this.httpClient.get('http://127.0.0.1:8080/api/users/funds?username=' + jwt.username)
               .subscribe(
                   (response: any) => {
                     this.fundsService.funds$ = response.funds
                       console.log('Response:', response);
                   },
                   (error) => {
                       console.error('Error:', error);
                   }
               );
           ;

       }
     });
  }

  getDecodedAccessToken(token: string): JwtPayload | null{
    try {
      // Use jwt_decode to decode the token
      return jwtDecode(token) as JwtPayload;
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }

  navigate(route:string) {
    this.router.navigate([route]);
  }

  navigateToLogin() {
    this.router.navigate(["auth"]);
    this.authService.showLoginForm();
  }

  navigateToRegister() {
    this.router.navigate(["auth"]);
    this.authService.showRegisterForm();
  }
  logout():void{
    this.authService.logout();
  }

}
