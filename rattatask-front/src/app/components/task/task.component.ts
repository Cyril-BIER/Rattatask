import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Task } from '../../interfaces/Task';
import { MatButtonModule } from '@angular/material/button';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-task',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
  constructor(private taskService: TasksService) {}

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
  @Input() task!: Task;
}
