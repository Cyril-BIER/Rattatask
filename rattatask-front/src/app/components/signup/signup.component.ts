import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../services/auth.service';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'], // Corrected 'styleUrl' to 'styleUrls'
})
export class SignupComponent {
  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
    confirmPassword: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService) {}

  onSubmit(): void {
    if (this.form.valid) {
      const { email, password, confirmPassword } = this.form.value;

      if (password === confirmPassword) {
        this.authService
          .signup(email, password)
          .pipe(
            catchError((error) => {
              console.log(error);
              return of(false);
            })
          )
          .subscribe(() => {
            alert('Compte créé');
            location.reload();
          });
      } else {
        alert('Les mots de passe ne correspondent pas!');
      }
    } else {
      alert('Veuillez remplir correctement tous les champs!');
    }
  }

  // Helper method to check if email is invalid
  get emailError(): string | null {
    const emailControl = this.form.get('email');
    if (emailControl?.hasError('required')) {
      return "L'email est requis.";
    }
    if (emailControl?.hasError('email')) {
      return 'Veuillez entrer une adresse email valide.';
    }
    return null;
  }
}
