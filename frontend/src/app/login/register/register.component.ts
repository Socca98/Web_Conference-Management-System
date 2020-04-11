import { Component, OnInit } from '@angular/core';
import {User} from '../../shared/interfaces/user';
import {LoginService} from '../../shared/services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [LoginService]
})
export class RegisterComponent implements OnInit {
  user: User = {
    username: null,
    password: null,
    affiliation: null,
    email: null,
    name: null,
  webpage: null,
  };
  users: User[] = [];
  
  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.getUsers().subscribe((result: User[]) =>
      this.users = result
    );
  }

}
