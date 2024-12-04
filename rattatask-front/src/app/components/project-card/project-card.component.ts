import { Component, Input } from '@angular/core';
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
export class ProjectCardComponent {
  @Input() project!: Project;

  constructor(private projectService:ProjectService){}

  delete() {
    this.projectService.deleteProject([this.project.id]).subscribe({
      next(){
        alert("Projet supprimé");
        location.reload();
      },
      error:(err)=>{
        console.error(err);
      }
    })
  }
}
