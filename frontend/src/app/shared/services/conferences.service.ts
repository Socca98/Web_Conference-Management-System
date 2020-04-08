import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../interfaces/user';
import {Observable} from 'rxjs';
import {Conference} from '../interfaces/conference';

@Injectable({
  providedIn: 'root'
})
export class ConferencesService {

  constructor(private http: HttpClient) { }

  getConference(conferenceId: string): Observable<Conference> {
    return this.http.get<Conference>('http://localhost:8080/cms/api/conferences/' + conferenceId);
  }
}
