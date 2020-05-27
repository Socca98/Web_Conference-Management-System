import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Conference} from '../models/conference';
import {environment} from '../../../environments/environment';
import {Section} from '../models/section';
import {Review} from '../models/review';

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
    return this.http.get<Conference>(environment.apiEndpoint + '/conferences/' + conferenceId);
  }

  /**
   * Returns all Conferences.
   */
  getConferences(): Observable<Conference[]> {
    return this.http.get<Conference[]>(environment.apiEndpoint + '/conferences');
  }

  /**
   * Add a new section for a conference.
   * @param conferenceId the conference
   * @param section the new one
   */
  addSection(conferenceId, section): Observable<Section> {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.post<Section>(
      environment.apiEndpoint + '/conferences/' + conferenceId + '/submissions/section',
      section);
  }

  /**
   * Returns all sections for a conference. These are displayed in tab-sections.
   * @param conferenceId the conference
   */
  getSections(conferenceId): Observable<Section[]> {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.get<Section[]>(environment.apiEndpoint + 'conferences/' + conferenceId + '/sections/');
  }
}
