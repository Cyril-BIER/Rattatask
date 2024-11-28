import { Component } from '@angular/core';
import { ProjectCardComponent } from '../project-card/project-card.component';
import { Project } from '../interfaces/Project';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule, ProjectCardComponent],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css',
})
export class ProjectsComponent {
  projects: Project[] = [
    { id: 1, name: 'Mon Projet' },
    { id: 2, name: 'Mon Projet2' },
  ];
}
