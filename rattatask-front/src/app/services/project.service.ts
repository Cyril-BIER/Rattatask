import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ENV } from '../../environments/env';
import { catchError, map, Observable } from 'rxjs';
import { Project } from '../interfaces/Project';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    const TOKEN = localStorage.getItem('token');
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${TOKEN}`,
    });
  }

  getProjects() {
    return this.http
      .get<any>(`${ENV.apiUrl}/api/projects`, { headers: this.headers })
      .pipe(
        map((response) => {
          return response;
        }),
        catchError((error) => {
          return error;
        })
      );
  }

  postProject(name: string) {
    const body = { name: name };

    return this.http
      .post<any>(`${ENV.apiUrl}/api/projects`, body, { headers: this.headers })
      .pipe(
        map((response) => {
          return response;
        }),
        catchError((error) => {
          return error;
        })
      );
  }
}
