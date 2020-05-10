import {Component, Input, OnInit} from '@angular/core';
import {Conference} from '../../models/conference';

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {
  @Input()
  conference: Conference;

  constructor() { }

  ngOnInit(): void {
  }

}
