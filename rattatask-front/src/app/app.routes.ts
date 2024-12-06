import { Routes } from '@angular/router';
import { ProjectsComponent } from './components/projects/projects.component';
import { TaskBoardComponent } from './components/task-board/task-board.component';
import { UserTasksComponent } from './components/user-tasks/user-tasks.component';
import { ProfilComponent } from './components/profil/profil.component';

export const routes: Routes = [
  { path: '', component: ProjectsComponent },
  {path:'project/:id', component:TaskBoardComponent},
  {path:'tasks', component:UserTasksComponent},
  {path:'profile', component:ProfilComponent}
];
