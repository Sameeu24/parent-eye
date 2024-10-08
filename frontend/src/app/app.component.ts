import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ParentDashboardComponent } from "./parent-dashboard/parent-dashboard.component";
import { TeacherDashboardComponent } from './teacher-dashboard/teacher-dashboard.component';
import { ContentmanagementComponent } from './contentmanagement/contentmanagement.component';
import { AssignmentDashboardComponent } from './assignment-dashboard/assignment-dashboard.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, ParentDashboardComponent,TeacherDashboardComponent,ContentmanagementComponent,AssignmentDashboardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
