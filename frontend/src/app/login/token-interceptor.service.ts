import {Injectable, Injector} from '@angular/core';
import {HttpInterceptor} from '@angular/common/http';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(
    private injector: Injector,
  ) {
  }

  intercept(req, next) {
    const authService = this.injector.get(AuthService);

    // If not logged, do not set Authorization in Headers
    if (!authService.isLogged()) {
      return next.handle(req);
    }

    const tokenizedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${authService.getToken()}`
      }
    });
    return next.handle(tokenizedReq);
  }
}
