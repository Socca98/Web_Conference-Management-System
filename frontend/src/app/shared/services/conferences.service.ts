import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Conference} from '../models/conference';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConferencesService {

  constructor(private http: HttpClient) {
  }

  /**
   * Returns the JSON of one Conference.
   * @param conferenceId Id of the target Conference
   */
  getConference(conferenceId: string): Observable<Conference> {
    return this.http.get<Conference>(environment.apiEndpoint + '/conferences' + conferenceId);
  }

  /**
   * Returns all Conferences.
   */
  getConferences(): Observable<Conference[]> {
    return this.http.get<Conference[]>(environment.apiEndpoint + '/conferences');
  }
}
