import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Submission} from '../models/submission';
import {Verdict} from '../models/verdict';
import {Review} from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class SubmissionsService {

  constructor(private http: HttpClient) {
  }

  /**
   * Returns all the submissions for a conference.
   */
  getSubmissions(conferenceId): Observable<Submission[]> {
    if (conferenceId === null) {
      throw new Error('Error! Could not retrieve conference id!');
    }
    return this.http.get<Submission[]>(environment.apiEndpoint + '/conferences/' + conferenceId + '/submissions');
  }

  /**
   * Returns all reviews for one user (known from token)
   * assigned to the current user (in Assign Papers phase).
   */
  getReviews(conferenceId): Observable<Review[]> {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.get<Review[]>(environment.apiEndpoint + '/conferences/' + conferenceId + '/reviews');
  }

  /**
   * Returns all reviews that have Submission id contained in reviews of the current user.
   * All reviews with same submission as current user.
   */
  getReviewsOthers(conferenceId): Observable<Review[]> {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.get<Review[]>(environment.apiEndpoint + '/conferences/' + conferenceId + '/reviews/others');
  }

  sendReview(conferenceId, submissionId, reviewId, verdict: Verdict) {
    return this.http.put<Submission[]>(
      environment.apiEndpoint + '/conferences/' + conferenceId + '/submissions/' + submissionId + '/review/' + reviewId,
      verdict
    );
  }
}
