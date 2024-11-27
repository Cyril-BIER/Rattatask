import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ProjectsComponent } from './projects/projects.component';

export const routes: Routes = [
  {path:'', component:LoginComponent},
  {path:'projects',component:ProjectsComponent}
];
