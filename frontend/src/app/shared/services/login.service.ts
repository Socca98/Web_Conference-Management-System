import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../interfaces/user';
import {Observable} from 'rxjs';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  getUsers() {
    return this.http.get(
      'http://localhost:8080/cms/api/users'
    );
  }


  postUser(user: User): Observable<string> {
    return this.http.post<string>('http://localhost:8080/cms/api/users/session', user);
  }
}
