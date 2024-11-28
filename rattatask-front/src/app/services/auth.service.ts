import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from 'rxjs';

import { ENV } from '../../environments/env';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
    this.loggedIn.next(this.isLoggedIn());
  }

  login(email: string, password: string): Observable<boolean> {
    const credentials = {
      email: email,
      password: password,
    };

    return this.http.post<any>(`${ENV.apiUrl}/auth/login`, credentials).pipe(
      map((response) => {
        localStorage.setItem('user', response.username);
        localStorage.setItem('user_id', response.id);
        localStorage.setItem('token', response.generatedToken);
        localStorage.setItem('tokenExpiresAt', Date.now() + response.expiresIn);
        this.loggedIn.next(true);
        return true;
      }),
      catchError((error) => {
        this.loggedIn.next(false);
        return of(false);
      })
    );
  }

  signup(email: string, password: string) : Observable<boolean>{
    const credentials = {
      email: email,
      password: password,
    };

    return this.http.post<any>(`${ENV.apiUrl}/auth/signup`,credentials).pipe(
      map(()=>{
        return true;
      }), catchError((error) => {
        return of(false);
      })
    );
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
