import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../shared/models/user';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {Token} from '../shared/models/token';
import {Conference} from '../shared/models/conference';

@Injectable()
export class AuthService {
  private _conference: Conference;
  private _user: User;

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
  }


  get conference(): Conference {
    return this._conference;
  }

  set conference(value: Conference) {
    this._conference = value;
  }

  get user(): User {
    if (!this.isLogged()) {
      throw Error('User not logged!');
    }
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  loginUser(user: User): Observable<Token> {
    return this.http.post<Token>('http://localhost:8080/cms/api/login', user);
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>('http://localhost:8080/cms/api/register', user);
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
    this.router.navigate(['']);
  }

  getUserInformation(selectedConferenceId) {
    if (selectedConferenceId === null) {
      throw new Error('Selected conference Id is null!');
    }

    const params = new HttpParams().set('conferenceId', selectedConferenceId);
    return this.http.get(environment.apiEndpoint + '/user-information', {params});
  }

  getUserRole() {
    if (localStorage.getItem('chair')) {
      return 'Chair';
    }
    if (localStorage.getItem('role') !== null) {
      return localStorage.getItem('role');
    }
    return null;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  /**
   *
   */
  isOnConferenceSite() {
    return !!localStorage.getItem('conferenceId');
  }
}
