import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Conference} from '../models/conference';
import {environment} from '../../../environments/environment';

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
    const conferenceId = this.localStorage.getItem('conferenceId');
    if(conferenceId === null) {
        throw new Error('Error! Could not retrieve conference id!');
    }
    return this.http.get<Submission[]>(environment.apiEndpoint + '/' + conferenceId + '/submissions');
  }
}
