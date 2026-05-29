import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private API = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  updateName(name: string) {
    return this.http.put(`${this.API}/me`, { name });
  }

  deleteUserMe() {
    return this.http.delete(`${this.API}/me`);
  }
}
