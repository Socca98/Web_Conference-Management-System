import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot,} from '@angular/router';
import {AuthService} from './auth.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Role} from '../shared/models/role';

@Injectable({
  providedIn: 'root',
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
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    // const currentUser = this.authService.currentUserValue;

    if (this.authService.isLogged()) {
      // check if route is restricted by role
      if ((route.data.roles && route.data.roles.indexOf(this.authService.getUserRole()) === -1) &&
        this.authService.getUserRole() !== Role.Chair) {
        // Role not authorized, restrict!
        // this.router.navigate(['/conference/details']);
        this.snackBar.open('You are not authorized!', 'Ok:(', {
          duration: 2000,
        });
        return false;
      }
      // authorized so return true
      return true;
    }
    // Not logged in, so redirect to login page with the return url
    // this.router.navigate([''], {queryParams: {returnUrl: state.url}});
    this.router.navigate(['/']);
    this.snackBar.open('Please sign in!', 'Ok:(', {
      duration: 2000
    });
    return false;
  }
}
