import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../services/auth.service';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [MatCardModule, MatInputModule, MatButtonModule, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  form: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });

  constructor(private authService:AuthService){}

  onSubmit(): void{
    if(this.form.valid){
      const { email, password, confirmPassword } = this.form.value;
      if(password == confirmPassword){
        this.authService.signup(email, password).subscribe(() => {
          catchError((error) => {
            console.log(error);
            return of(false);
          });
        });
      }else{
        alert("Les mots de passe ne correspondent pas!")
      }

    }
  }
}
