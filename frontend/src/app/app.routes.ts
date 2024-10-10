import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ParentDashboardComponent } from './parent-dashboard/parent-dashboard.component';
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';
import { AssignmentDashboardComponent } from './assignment-dashboard/assignment-dashboard.component';
import { ContentmanagementComponent } from './contentmanagement/contentmanagement.component';
export const routes: Routes = [
    {path:'',redirectTo:'/login',pathMatch:'full'},
    {path:'login',component:LoginComponent},
    {path:'parent-dashboard',component:ParentDashboardComponent},
    {path:'teacher-dashboard',component:TeacherDashboardComponent},
    {path:'assignment-dashboard',component:AssignmentDashboardComponent},
    {path:'contentmanagement-dashboard',component:ContentmanagementComponent}





];
