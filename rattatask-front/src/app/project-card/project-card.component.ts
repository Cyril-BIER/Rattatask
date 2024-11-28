import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Project } from '../interfaces/Project';

@Component({
  selector: 'app-project-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css'
})
export class ProjectCardComponent {
  @Input()
  project!: Project;
}
