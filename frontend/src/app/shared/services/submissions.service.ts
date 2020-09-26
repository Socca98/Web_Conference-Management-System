import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Submission} from '../models/submission';
import {Verdict} from '../models/verdict';
import {Review} from '../models/review';
import {User} from '../models/user';

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
    return this.http.get<Submission[]>(environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions');
  }

  /**
   * Returns all reviews for one user (known from token)
   * assigned to the current user (in Assign Papers phase).
   */
  getReviews(conferenceId): Observable<Review[]> {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.get<Review[]>(environment.apiEndpoint + 'conferences/' + conferenceId + '/reviews');
  }

  /**
   * Returns all reviews that have Submission id contained in reviews of the current user.
   * All reviews with same submission as current user.
   */
  getReviewsOthers(conferenceId): Observable<Review[]> {
    if (conferenceId === null) {
      throw new Error('Error! Conference id is null for request!');
    }
    return this.http.get<Review[]>(environment.apiEndpoint + 'conferences/' + conferenceId + '/review/others');
  }

  createReview(conferenceId, paramsReview: Review) {
    const verdictBody = {
      user: {
        email: paramsReview.user.email,
      } as User,
      verdict: Verdict.Not_Reviewed,
      recommendation: null,
    };
    return this.http.post(
      environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/' + paramsReview.submission.id + '/review',
      verdictBody
    );
  }

  updateReview(conferenceId, submissionId, review: Review): Observable<Review> {
    return this.http.put<Review>(
      environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/' + submissionId + '/review/' + review.reviewId,
      {
        verdict: review.verdict,
        recommendation: review.recommendation
      }
    );
  }

  sendFinalVerdict(conferenceId, submissionId, verdict: Verdict) {
    return this.http.post<Review>(
      environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/' + submissionId + '/final-verdict',
      verdict
    );
  }

  likeSubmission(conferenceId, submissionId) {
    return this.http.post(environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/' + submissionId + '/like', null);
  }

  unlikeSubmission(conferenceId, submissionId) {
    return this.http.delete(environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/' + submissionId + '/like');
  }

  addAbstract(conferenceId, submission: Submission) {
    return this.http.post<Submission>(environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/', submission);
  }

  uploadFile(formData: FormData) {
    return this.http.post(environment.apiEndpoint + 'files/upload', formData);
  }

  downloadFile(fileId: string) {
    window.open(environment.apiEndpoint + 'files/download/' + fileId, '_blank');
  }

  /**
   * Attach full paper by updating the corresponding field in the Submission object.
   * @param conferenceId Current conference.
   * @param submission Update fullpaper for this submission.
   */
  addFullPaper(conferenceId, submission: Submission) {
    const urlString = environment.apiEndpoint + '/conferences/' + conferenceId + '/submissions/' + submission.id;
    return this.http.put(urlString, submission);
  }

  editAbstract(conferenceId, submission: Submission) {
    return this.http.put<Submission>(environment.apiEndpoint + 'conferences/' + conferenceId + '/submissions/' + submission.id, submission);
  }
}
