import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    LoginComponent,
    SignupComponent,
    CommonModule,
    RouterLink,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Rattatask';

  disconnect() {
    localStorage.clear();
    location.reload();
  }

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
