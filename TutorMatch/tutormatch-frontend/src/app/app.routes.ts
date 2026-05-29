import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login';
import { RegisterComponent } from './auth/register/register';
import { DashboardComponent } from './dashboard/dashboard';
import { EditProfileComponent } from './edit-profile/edit-profile';
import { inject } from '@angular/core';
import { AuthService } from './service/auth';

const authGuard = () => {
  const auth = inject(AuthService);
  return auth.isLoggedIn();
};

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'edit-profile', component: EditProfileComponent, canActivate: [authGuard] }
];
