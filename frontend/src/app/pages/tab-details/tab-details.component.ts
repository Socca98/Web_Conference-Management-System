import {Component, Input, OnInit} from '@angular/core';
import {Conference} from '../../shared/models/conference';
import {AuthService} from '../../login/auth.service';

@Component({
  selector: 'app-tab-details',
  templateUrl: './tab-details.component.html',
  styleUrls: ['./tab-details.component.css'],
})
export class TabDetailsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
