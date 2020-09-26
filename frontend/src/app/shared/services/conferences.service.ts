import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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
   * Add a new conference
   */
  addConference(newConference: Conference): Observable<Conference> {
    return this.http.post<Conference>(environment.apiEndpoint + '/conferences',
      newConference);
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
      environment.apiEndpoint + '/conferences/' + conferenceId + '/sections',
      section);
  }

  /**
   * Returns all sections for a conference. These are displayed in tab-sections.
   * @param conferenceId the conference
   */
  getSections(conferenceId) {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.get<Section[]>(environment.apiEndpoint + 'conferences/' + conferenceId + '/sections');
  }

  /**
   * Increase the number of occupied seats for a section.
   * @param conferenceId Id of conference
   * @param sectionId Id of section
   */
  attendSection(conferenceId, sectionId) {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.post<Section>(environment.apiEndpoint + '/conferences/' + conferenceId + '/sections/' + sectionId + '/attend', {});
  }
}
