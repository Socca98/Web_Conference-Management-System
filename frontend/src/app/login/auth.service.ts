import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../shared/models/user';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';

@Injectable()
export class AuthService {

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
  }

  loginUser(user: User): Observable<string> {
    return this.http.post<string>('http://localhost:8080/cms/api/users/login', user);
  }

  registerUser(user: User): Observable<string> {
    return this.http.post<string>('http://localhost:8080/cms/api/users/register', user);
  }

  /**
   * Checks if the token exists in the Local Storage.
   * !! - returns true if object exists, false if its null
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

  getUserInformation(conferenceId) {
    if (conferenceId === null) {
      throw new Error('Conference Id is null!');
    }

    const params = new HttpParams().set('conferenceId', conferenceId);
    return this.http.get(environment.apiEndpoint + '/user-information', {params});
  }

  getUserRole() {
    return localStorage.getItem('role');
  }
}
