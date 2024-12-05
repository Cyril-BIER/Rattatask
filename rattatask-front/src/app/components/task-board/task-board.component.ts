import { Component, inject, Input, OnInit } from '@angular/core';
import { ProjectService } from '../../services/project.service';
import { Project } from '../../interfaces/Project';
import { Task } from '../../interfaces/Task';
import { CommonModule } from '@angular/common';
import { TaskComponent } from '../task/task.component';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { CreateTaskComponent } from '../create-task/create-task.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-task-board',
  standalone: true,
  imports: [CommonModule, TaskComponent, MatButtonModule, MatIconModule],
  templateUrl: './task-board.component.html',
  styleUrl: './task-board.component.css',
})
export class TaskBoardComponent implements OnInit {
  private route = inject(ActivatedRoute);
  project!: Project;
  tasks: { [key: string]: any[] } = {
    TODO: [],
    ONGOING: [],
    DONE: [],
  };

  constructor(
    private projectService: ProjectService,
    public dialog: MatDialog
  ) {}

  @Input() projectID!: number;

  openTaskCreatorDialog(): void {
    const dialogRef = this.dialog.open(CreateTaskComponent, {
      width: 'fit-content',
      data: { projectID: this.projectID },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('Dialog closed. Result:', result);
    });
  }

  ngOnInit(): void {
    this.projectID = this.route.snapshot.params['id'];

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
