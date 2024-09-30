import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  error: string = '';

  constructor(private authService: AuthService) {}
  onSubmit() {
    console.log(`Attempting to login with username: ${this.username} and password: ${this.password}`);
    if (this.authService.login(this.username, this.password)) {
      this.error = '';
    } else {
      this.error = 'Invalid credentials';
    }
  }
  

}
