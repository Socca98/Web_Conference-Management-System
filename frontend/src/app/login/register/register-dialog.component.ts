import { Component, OnInit } from '@angular/core';
import {User} from '../../shared/interfaces/user';
import {LoginService} from '../../shared/services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.css'],
  providers: [LoginService]
})
export class RegisterDialogComponent implements OnInit {
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
