import { Component, inject, Input } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { ProjectService } from '../../services/project.service';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { catchError, of } from 'rxjs';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { Task } from '../../interfaces/Task';

@Component({
  selector: 'app-create-task',
  standalone: true,
  imports: [
    MatDialogModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatFormFieldModule,
    CommonModule,
    MatCardModule,
  ],
  templateUrl: './create-task.component.html',
  styleUrl: './create-task.component.css',
})
export class CreateTaskComponent {
  @Input() projectId !: number;
  users: { id: number; name: string }[] = [
    { id: 1, name: 'Alice' },
    { id: 2, name: 'Bob' },
    { id: 3, name: 'Charlie' },
  ];
  constructor(private projectService: ProjectService, private fb: FormBuilder) {
    this.form = this.fb.group({
      nom: [''],
      description: [''],
      userIds: [[]],
    });
  }
  form: FormGroup;

  onSubmit(): void {

    const task = {
      name : this.form.value.nom,
      description : this.form.value.description
    };
    if (this.form.valid) {
      this.projectService.postTask(this.projectId, task).subscribe(() => {
        catchError((error) => {
          console.log(error);
          return of(false);
        });
      });
      location.reload();
    }
  }
}
