import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { book } from '../book';
import { AppRoutingModule } from '../app-routing.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
view(arg0: String) {
throw new Error('Method not implemented.');
}
update(id: number) {
  this.router.navigate(['editBook', id]);
}

  content?: string;
  Books: book[] | undefined;
  constructor(private userService: UserService, private tokenStorage: TokenStorageService, private router: Router, private authService: AuthService) { }

  private getBooks(){
    this.authService.getBooksList().subscribe(data => {
      this.Books = data;
    });
  }

   blockunblock(userid: number,blockunblock: string) {
    this.authService.blockunblock(userid, blockunblock).subscribe(data => {
      this.getBooks();
    }
    ,
      err => {
        if(err.status == 200){
          setTimeout(() => {
            setTimeout(() => {
              this.getBooks();
            });
          }, 3000);
        }
        this.getBooks();
      });
    
  }


  ngOnInit(): void {
    this.getBooks();
    if(this.tokenStorage.getToken() === ""){
      window.location.href = location.origin+"/login";
    }
  }
}