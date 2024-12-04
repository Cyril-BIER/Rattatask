import { Component, Inject, inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { ProjectService } from '../../services/project.service';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { catchError, of } from 'rxjs';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { User } from '../../interfaces/User';
import { UsersService } from '../../services/users.service';

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
  projectId!: number;
  form: FormGroup;
  users: User[] = [];

  constructor(
    private projectService: ProjectService,
    private userService: UsersService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<CreateTaskComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { projectID: number }
  ) {
    this.projectId = data.projectID;
    this.userService.getUsers().subscribe((response) => {
      this.users = response;
    });

    this.form = this.fb.group({
      nom: [''],
      description: [''],
      userIds: [[]],
    });
  }

  onSubmit(): void {
    const task  = {
      name: this.form.value.nom,
      description: this.form.value.description,
      usersID: this.form.value.userIds
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

  closeDialog(): void {
    this.dialogRef.close();
  }
}
