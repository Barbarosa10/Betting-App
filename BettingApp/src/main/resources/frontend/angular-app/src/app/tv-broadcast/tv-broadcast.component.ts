import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { TvBroadcastService } from "./tv-broadcast.service";
import {CommonModule} from "@angular/common";
@Component({
  selector: 'app-tv-broadcast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tv-broadcast.component.html',
  styleUrls: ['./tv-broadcast.component.css']
})
export class TvBroadcastComponent implements OnInit {

  digi1Url?: SafeResourceUrl// Use SafeResourceUrl type
  digi2Url?: SafeResourceUrl
  digi3Url?: SafeResourceUrl
  digi4Url?: SafeResourceUrl
  constructor(
    private tvBroadcastService: TvBroadcastService,
    private sanitizer: DomSanitizer // Inject the DomSanitizer
  ) { }

  ngOnInit(): void {
    this.tvBroadcastService.getBroadcastUrl('digi1').subscribe(url => {
      this.digi1Url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
    });
    this.tvBroadcastService.getBroadcastUrl('digi2').subscribe(url => {
      this.digi2Url = this.sanitizer.bypassSecurityTrustResourceUrl( url);
    });
    this.tvBroadcastService.getBroadcastUrl('digi3').subscribe(url => {
      this.digi3Url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
    });
    this.tvBroadcastService.getBroadcastUrl('digi4').subscribe(url => {
      this.digi4Url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
    });
  }
}
