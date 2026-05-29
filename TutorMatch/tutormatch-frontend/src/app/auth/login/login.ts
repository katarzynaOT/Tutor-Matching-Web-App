import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {
  model = { email: '', password: '' };
  message: string = '';

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

  onSubmit() {
    this.http.post<any>('http://localhost:8080/auth/login', this.model).subscribe({
      next: (res: any) => {
        this.message = 'Logowanie udane!';
        if (res.token) {
          this.authService.setToken(res.token);
        }
        this.router.navigate(['/dashboard']);
      },
      error: (err: any) => {
        this.message = 'Błąd logowania: ' + (err.error?.message || 'spróbuj ponownie');
      }
    });
  }
}
