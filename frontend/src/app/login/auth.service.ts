import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../shared/models/user';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {Token} from '../shared/models/token';
import {Conference} from '../shared/models/conference';
import {Role} from '../shared/models/role';

@Injectable()
export class AuthService {

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
  }

  get conference(): Conference {
    const conference = JSON.parse(localStorage.getItem('conference'));
    return conference;
  }

  set conference(value: Conference) {
    localStorage.setItem('conference', JSON.stringify(value));
  }

  get user(): User {
    const user = JSON.parse(localStorage.getItem('user'));
    return user;
  }

  set user(value: User) {
    localStorage.setItem('user', JSON.stringify(value));
  }

  loginUser(user: User): Observable<Token> {
    return this.http.post<Token>(environment.apiEndpoint + 'login', user);
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(environment.apiEndpoint + 'register', user);
  }

  registerUserInvitationLink(user: User, linkId: string): Observable<User> {
    return this.http.post<User>(environment.apiEndpoint + 'invitation/' + linkId, user);
  }

  /**
   * Checks if the token exists in the Local Storage.
   * !! - returns true if object exists, false if its null (when we are not interested in the value)
   */
  isLogged() {
    return !!localStorage.getItem('token');
  }

  /**
   * Logout the current user and redirect him to the home page.
   */
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    localStorage.removeItem('conference');
    this.router.navigate(['']);
  }

  getUserInformation(selectedConferenceId) {
    if (selectedConferenceId === null) {
      throw new Error('Selected conference Id is null!');
    }

    const params = new HttpParams().set('conferenceId', selectedConferenceId);
    return this.http.get(environment.apiEndpoint + 'user-information', {params});
  }

  getUserRole() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user.isChair) {
      return Role.Chair;
    }
    return user.role;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isPassedAbstractDeadline(): boolean {
    const nowTime = new Date().getTime();
    return nowTime <= this.conference.abstractDeadline  * 1000;
  }

  isPassedEvaluationDeadline(): boolean {
    const nowTime = new Date().getTime();
    return nowTime <= this.conference.evaluationDeadline * 1000;
  }
}
