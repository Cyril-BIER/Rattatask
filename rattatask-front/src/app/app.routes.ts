import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProjectsComponent } from './components/projects/projects.component';

export const routes: Routes = [
  { path: '', component: ProjectsComponent }
];
