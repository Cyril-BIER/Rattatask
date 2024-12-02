import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    LoginComponent,
    SignupComponent,
    CommonModule,
    RouterLink,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Rattatask';
  id = 1;

  private isTokenExpired(): boolean {
    const tokenExpiresAt = localStorage.getItem('tokenExpiresAt');
    if (!tokenExpiresAt) {
      return true;
    }
    const expirationTime = parseInt(tokenExpiresAt, 10);
    const currentTime = Date.now();
    return currentTime > expirationTime;
  }

  isAuthenticated = localStorage.getItem('token') && this.isTokenExpired;
}
