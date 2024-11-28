import { Component } from '@angular/core';
import { ProjectCardComponent } from '../project-card/project-card.component';
import { Project } from '../../interfaces/Project';
import { CommonModule } from '@angular/common';
import { ProjectService } from '../../services/project.service';
import { CreateProjectComponent } from '../create-project/create-project.component';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule, ProjectCardComponent, CreateProjectComponent],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css',
})
export class ProjectsComponent {
  constructor(private projectService: ProjectService) {}

  projects !: Project[];
  username = localStorage.getItem('user');

  ngOnInit(): void {
    this.projectService.getProjects().subscribe((projects)=>{
      this.projects = projects;
    })
  }
}
