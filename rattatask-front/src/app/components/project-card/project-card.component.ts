import { Component, Input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Project } from '../../interfaces/Project';
import { RouterLink } from '@angular/router';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-project-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, RouterLink],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css',
})
export class ProjectCardComponent implements OnInit {
  @Input() project!: Project;
  numberOfTodo: number = 0;
  numberOfOnGoing: number = 0;
  numberOfDone: number = 0;

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {
    if (this.project && this.project.tasks) {
      this.numberOfTodo = this.project.tasks.filter(
        (task) => task.status === 'TODO'
      ).length;
      this.numberOfOnGoing = this.project.tasks.filter(
        (task) => task.status === 'ONGOING'
      ).length;
      this.numberOfDone = this.project.tasks.filter(
        (task) => task.status === 'DONE'
      ).length;
    }
  }

  delete() {
    this.projectService.deleteProject([this.project.id]).subscribe({
      next() {
        alert('Projet supprimé');
        location.reload();
      },
      error: (err) => {
        console.error(err);
      },
    });
  }
}
