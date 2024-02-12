import { Routes } from '@angular/router';
import {AuthComponent} from "./auth/auth.component";
import {AppComponent} from "./app.component";
import {BiletComponent} from "./bilet/bilet.component";
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";
import {LootboxComponent} from "./lootbox/lootbox.component";
import {SportComponent} from "./sport/sport.component";
import {TvBroadcastComponent} from "./tv-broadcast/tv-broadcast.component";
import {NewsComponent} from "./news/news.component";
import {TicketResultComponent} from "./ticket-result/ticket-result.component";

export const routes: Routes = [
  {path: '',component:HomeComponent},
  {path: 'auth',component:AuthComponent},
  {path: 'register',component:RegisterComponent},
  {path: 'buy-coins',component:LootboxComponent},
  {path: 'register',component:RegisterComponent},
  {path:'sports',component:SportComponent},
  {path:'live',component:TvBroadcastComponent},
  {path:'news',component:NewsComponent},
  {path:'super-secret-ticket-update',component:TicketResultComponent}
];
