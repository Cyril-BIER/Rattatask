import { Component, Input, OnInit } from '@angular/core';
import { ProjectService } from '../../services/project.service';
import { Project } from '../../interfaces/Project';
import { Task } from '../../interfaces/Task';
import { CommonModule } from '@angular/common';
import { TaskComponent } from '../task/task.component';

@Component({
  selector: 'app-task-board',
  standalone: true,
  imports: [CommonModule, TaskComponent],
  templateUrl: './task-board.component.html',
  styleUrl: './task-board.component.css',
})
export class TaskBoardComponent implements OnInit {
  project !: Project;
  tasks: { [key: string]: any[] } = {
    TODO: [],
    ONGOING: [],
    DONE: [],
  };

  constructor(private projectService: ProjectService) {}

  @Input() projectID!: number;

  ngOnInit(): void {
    this.projectService.getProjects([this.projectID]).subscribe({
      next: (response) => {
        this.project = response[0];
        this.tasks = {
          TODO: this.project.tasks.filter(
            (task: Task) => task.status === 'TODO'
          ),
          ONGOING: this.project.tasks.filter(
            (task: Task) => task.status === 'ONGOING'
          ),
          DONE: this.project.tasks.filter(
            (task: Task) => task.status === 'DONE'
          ),
        };
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}
