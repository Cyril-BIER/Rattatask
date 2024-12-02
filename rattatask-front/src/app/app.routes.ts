import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProjectsComponent } from './components/projects/projects.component';
import { TaskBoardComponent } from './components/task-board/task-board.component';

export const routes: Routes = [
  { path: '', component: ProjectsComponent },
  {path:'project/:id', component:TaskBoardComponent}
];
