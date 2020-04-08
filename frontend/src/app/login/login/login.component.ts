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
  user: User = {
    username: null,
    password: null
  };
  users: User[] = [];

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.getUsers().subscribe((result: User[]) =>
      this.users = result
    );
  }

  /**
   * Sends 'this.user' to LoginService.
   * Login button's click calls this function.
   * [(ngModel)] changes automatically the object 'this.user' if its fields change.
   */
  login() {
    this.loginService.postUser(this.user).subscribe({
      next: (response: string) => {
        alert(response);
      },
      error: err => {
        console.error('Error! ' + err);
        alert('Invalid user or password.');
      }
    });
  }

}
