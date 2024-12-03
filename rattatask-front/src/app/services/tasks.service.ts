import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ENV } from '../../environments/env';
import { catchError, map } from 'rxjs';
import { Task } from '../interfaces/Task';

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

  updateTask(task : Task){
    return this.http
    .put<any>(`${ENV.apiUrl}/api/task`, [task], {headers: this.headers})
    .pipe(
      map((response)=>{
        return response;
      }),
      catchError((error)=>{
        return error;
      })
    );
  }
}
