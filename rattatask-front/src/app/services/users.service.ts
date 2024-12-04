import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ENV } from '../../environments/env';
import { catchError, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    const TOKEN = localStorage.getItem('token');
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${TOKEN}`,
    });
  }

  getUsers(ids?:number[]){
    if (ids == undefined) {
      return this.http
        .get<any>(`${ENV.apiUrl}/api/user`, { headers: this.headers })
        .pipe(
          map((response) => {
            return response;
          }),
          catchError((error) => {
            return error;
          })
        );
    } else {
      let url: string = `${ENV.apiUrl}/api/user?`;
      ids.forEach((id) => {
        url += `ids=${id}&`;
      });

      return this.http.get<any>(url, { headers: this.headers }).pipe(
        map((response) => {
          return response;
        }),
        catchError((error) => {
          return error;
        })
      );
    }
  }
}
