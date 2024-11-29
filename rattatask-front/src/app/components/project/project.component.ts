import { Component, Input } from '@angular/core';
import { Project } from '../../interfaces/Project';
import { ProjectService } from '../../services/project.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { TaskComponent } from '../task/task.component';

@Component({
  selector: 'app-project',
  standalone: true,
  imports: [CommonModule, TaskComponent],
  templateUrl: './project.component.html',
  styleUrl: './project.component.css',
})
export class ProjectComponent {
  constructor(private projectService: ProjectService) {}

  @Input() projectID!: number;
  project !: Project;
  dataLoaded: boolean = false;

  ngOnInit(): void {
    this.projectService
      .getProjects([this.projectID]).subscribe({
        next: (response)=>{
          this.project = response[0]
        },
        error :(err)=>{
          console.error(err)
        }
      })

  }

}
