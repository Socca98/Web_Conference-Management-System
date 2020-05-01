import {Injectable} from '@angular/core';
import {CanActivate, Router,} from '@angular/router';
import {AuthService} from './auth.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {
  }

  /**
   * Check if user is logged in.
   * If so, canActivate in router outlet will allow access to routes.
   * If not, redirect to home page.
   */
  canActivate() {
    if (this.authService.isLogged()) {
      return true;
    } else {
      this.router.navigate(['']);
      this.snackBar.open('Please sign in!', 'Ok:(', {
        duration: 2000
      });
    }
  }

}
