import { Injectable } from '@angular/core';
import {Router} from '@angular/router'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router:Router) { }

  login(username: string, password: string): boolean {
    if (username === 'parent' && password === '123') {
      this.router.navigate(['/parent-dashboard']);
      return true;
    } else if (username === 'teacher' && password === '456') {
      this.router.navigate(['/teacher-dashboard']);
      return true;
    }
    console.log('Login failed: Invalid credentials');
  return false;
  }
}

