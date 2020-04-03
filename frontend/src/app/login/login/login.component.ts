import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../shared/services/login.service';
import {User} from '../../shared/interfaces/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  users: User[] = [];

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.getUsers().subscribe((result: User[]) =>
      this.users = result
    );
  }

}
