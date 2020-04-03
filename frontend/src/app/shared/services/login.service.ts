import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get(
      'http://localhost:8080/cms/api/users'
    );
  }
}
