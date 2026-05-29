import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class RegisterComponent {
  model = { name: '', email: '', password: '', role: '' };
  message: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post<any>('http://localhost:8080/auth/register', this.model).subscribe({
      next: (res: any) => {
        this.message = 'Rejestracja udana!';
        this.router.navigate(['/dashboard']);
      },
      error: (err: any) => {
        this.message = 'Błąd rejestracji: ' + (err.error?.message || 'spróbuj ponownie');
      }
    });
  }
}
