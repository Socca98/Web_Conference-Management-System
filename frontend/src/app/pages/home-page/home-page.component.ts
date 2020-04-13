import {Component, OnInit} from '@angular/core';
import {Conference} from '../../shared/interfaces/conference';
import {ConferencesService} from '../../shared/services/conferences.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [ConferencesService],
})
export class HomePageComponent implements OnInit {
  conferences: Conference[] = [];

  constructor(private conferencesService: ConferencesService) {
  }

  ngOnInit(): void {
    this.conferencesService.getConferences().subscribe((result: Conference[]) => {
      this.conferences = result;
    });
  }

}
