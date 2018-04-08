import {Injectable} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';

@Injectable()
export class AuthService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string) {
    return this.http.post<any>(this.baseUrl + '/api/accounts/login', { email: email, password: password })
      .map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem('token', user.token);
        }

        return user;
      });
  }

  getToken() : string {
    return localStorage.getItem('token') === undefined ? "" : localStorage.getItem('token');
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
  }
}
