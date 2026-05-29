import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-profile.html',
})
export class EditProfileComponent {
  name: string = '';

  constructor(private userService: UserService, private router: Router) {
  }

  updateName() {
    console.log("NAME:", this.name);

    this.userService.updateName(this.name).subscribe({
      next: (res) => {
        alert('Zaktualizowano imię');
      },
      error: (err) => {
        console.error(err);
        alert('Błąd aktualizacji imienia');
      },
    });
  }

  deleteUser() {
    this.userService.deleteUserMe().subscribe({
      next: () => {
        alert('Konto usunięte');
        this.router.navigate(['/register']);
      },
      error: () => alert('Błąd usuwania'),
    });
  }
}
