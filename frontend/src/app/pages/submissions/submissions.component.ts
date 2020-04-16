import { Component, OnInit } from '@angular/core';
import {Proposal} from '../../shared/interfaces/proposal';

@Component({
    selector: 'app-submission',
    templateUrl: './submissions.component.html',
    styleUrls: ['./submissions.component.css']
})
export class SubmissionsComponent implements OnInit {
    proposal: Proposal = {
        title: null,
        abstract: null,
        name: null,
        topics: null,
        listOfAuthors: null,
        metaInformation: null,
        fullPaper: null
    };    
    proposals: Proposal[] = [this.proposal, this.proposal, this.proposal];

    constructor() { }

    ngOnInit(): void {
    }
}
