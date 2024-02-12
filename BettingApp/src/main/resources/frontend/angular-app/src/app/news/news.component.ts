import {Component, OnInit} from '@angular/core';
import {NewsService} from "./news.service";
import {CommonModule, NgForOf} from "@angular/common";
import {NewsModel} from "./NewsModel";

@Component({
  selector: 'app-news',
  standalone: true,
  imports: [NgForOf,CommonModule],
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})
export class NewsComponent implements OnInit {
  public news: NewsModel[] = [];

  constructor(private newsService: NewsService) {
  }

  public localImages = [
    'assets/Radu-Dragusin-Genoa.jpg',
    'assets/f1.jpg',
    'assets/Radu-Dragusin-campionat-de-top.png',
    'assets/f2.jpg',
    'assets/index.png',
    'assets/f3.jpg',
    'assets/images.jpg',
    'assets/f4.jpg',
    'assets/radu-dragusin3.jpg',
    'assets/f5.jpg'
  ];
  public expandedNewsIndex: number | null = null;

  getCardStyle(i: number) {
    if (this.expandedNewsIndex === i) {
      return {
        width: '100vw',
        height: 'auto',
        'max-height': '100vh',
        'z-index': '10',
        position: 'absolute',
        top: '0',
        left: '0'
      };
    }
    return {}; // Return default styles if not expanded
  }
  closeExpandedNews() {
    this.expandedNewsIndex = null;
  }
  toggleFullText(index: number): void {
    this.expandedNewsIndex = this.expandedNewsIndex === index ? null : index;
  }
  ngOnInit(): void {
    const country = 'england'; // Example, can be dynamic
    this.newsService.getNewsByCountry(country).subscribe(data => {
      this.news = data;
    });
  }
  sliceAfterTime(text: string, length: number = 60): string {
    const timePattern = /\d{2}:\d{2}/; // Matches 'HH:MM' format
    const match = text.match(timePattern);
    let slicedText = text;

    if (match && match.index !== undefined) {
      slicedText = text.slice(match.index + 6);
    }

    if (slicedText.length > length) {
      return slicedText.slice(0, length) + '...';
    }

    return slicedText;
  }


}

