import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Task } from '../../interfaces/Task';
import { MatButtonModule } from '@angular/material/button';
import { TasksService } from '../../services/tasks.service';
import { MatDialog } from '@angular/material/dialog';
import { UpdateTaskComponent } from '../update-task/update-task.component';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
@Input() task!: Task;

openUpdateTaskDialog() {
  const dialogRef = this.dialog.open(UpdateTaskComponent,{
    width: 'fit-content',
    data : this.task
  })
}
  constructor(private taskService: TasksService, public dialog : MatDialog) {}

  deleteTask() {
    this.taskService.deleteTask([this.task.id]).subscribe({
      next(value) {
        alert("Tâche supprimée");
        location.reload();
      },
      error: (err)=>{
        console.error(err);
      }
    })
  }

}
