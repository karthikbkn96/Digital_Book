import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { book } from '../book';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent {
view(arg0: String) {
throw new Error('Method not implemented.');
}


    
      content?: string;
      Books: book[] | undefined;
      constructor(private userService: UserService, private tokenStorage: TokenStorageService, private router: Router, private authService: AuthService) { }
    
      private getBooks(){
        this.authService.getSubscribedList().subscribe(data => {
          this.Books = data;
        });
      }
    
      ngOnInit(): void {
        this.getBooks();
        if(this.tokenStorage.getToken() === ""){
          window.location.href = location.origin+"/login";
        }
      }
    }