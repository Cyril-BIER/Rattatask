import { Component } from '@angular/core';
import { ProjectCardComponent } from '../project-card/project-card.component';
import { Project } from '../../interfaces/Project';
import { CommonModule } from '@angular/common';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule, ProjectCardComponent],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css',
})
export class ProjectsComponent {
  constructor(private projectService: ProjectService) {}

  projects !: Project[];

  ngOnInit(): void {
    this.projectService.getProjects().subscribe((projects)=>{
      this.projects = projects;
    })
  }
}
