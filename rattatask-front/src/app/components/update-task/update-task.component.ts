import { Component, Inject } from '@angular/core';
import { TasksService } from '../../services/tasks.service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Task } from '../../interfaces/Task';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-update-task',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatInputModule,
    CommonModule,
    MatDialogModule
  ],
  templateUrl: './update-task.component.html',
  styleUrl: './update-task.component.css',
})
export class UpdateTaskComponent {
  task: Task;
  form: FormGroup;

  closeDialog() {
    this.dialogRef.close();
  }
  onSubmit() {
    const updatedTask : Task = {
      id : this.task.id,
      name : this.task.name,
      description : this.form.value.description,
      status : this.form.value.status,
      userIds : []
    }
    this.taskService.updateTask(updatedTask).subscribe(()=>{
      catchError((error)=>{
        console.error(error);
        return of(false);
      });
    });
    location.reload();
  }

  constructor(
    private taskService: TasksService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<UpdateTaskComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Task
  ) {
    this.task = data;
    this.form = this.fb.group({
      description: this.task.description,
      status: this.task.status,
      userIds: [[]],
    });
  }

  status: { id: string; name: string }[] = [
    { id: 'TODO', name: 'A faire' },
    { id: 'ONGOING', name: 'En cours' },
    { id: 'DONE', name: 'Fait' },
  ];

  users: { id: number; name: string }[] = [
    { id: 1, name: 'Alice' },
    { id: 2, name: 'Bob' },
    { id: 3, name: 'Charlie' },
  ];
}
