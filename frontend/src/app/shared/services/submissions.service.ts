import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Submission} from '../models/submission';

@Injectable({
  providedIn: 'root'
})
export class SubmissionsService {

  constructor(private http: HttpClient) {
  }

  /**
   * Returns a JSON with all Conferences.
   */
  getSubmissions(): Observable<Submission[]> {
    const conferenceId = localStorage.getItem('conferenceId');
    if (conferenceId === null) {
      throw new Error('Error! Could not retrieve conference id!');
    }
    return this.http.get<Submission[]>(environment.apiEndpoint + '/' + conferenceId + '/submissions');
  }
}
