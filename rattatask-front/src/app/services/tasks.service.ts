import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ENV } from '../../environments/env';
import { catchError, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TasksService {
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    const TOKEN = localStorage.getItem('token');
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${TOKEN}`,
    });
  }

  deleteTask(ids: number[]) {
    let url: string = `${ENV.apiUrl}/api/task?`;
    ids.forEach((id) => {
      url += `ids=${id}&`;
    });
    return this.http.delete(url, { headers: this.headers }).pipe(
      map((response) => {
        return response;
      }),
      catchError((error) => {
        return error;
      })
    );
  }
}
