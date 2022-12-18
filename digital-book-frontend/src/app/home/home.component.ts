import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { book } from '../book';
import { AppRoutingModule } from '../app-routing.module';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  bookCount: boolean = false;

update(id: number) {
  this.router.navigate(['editBook', id]);
}


  content?: string;
  Books: book[] | undefined;
  constructor(private userService: UserService,private sanitizer: DomSanitizer, private tokenStorage: TokenStorageService, private router: Router, private authService: AuthService) { }

  private getBooks(){
    this.authService.getBooksList().subscribe(data => {
      debugger;
      this.Books = data;
      if(this.Books.length > 0){
        this.bookCount = true;
      }else{
        this.bookCount =false;
      }
    });
  }

  view(code:String){
    var byteArray = new Uint8Array(JSON.parse(code.valueOf()).data);
    var file = new Blob([byteArray], {type: JSON.parse(code.valueOf()).type});
    var fileURL = URL.createObjectURL(file);
    window.open(fileURL);
  }

  logo(code:String){
    var byteArray = new Uint8Array(JSON.parse(code.valueOf()).data);
    const STRING_CHAR = byteArray.reduce((data, byte)=> {
      return data + String.fromCharCode(byte);
      }, '');
      let base64String = btoa(STRING_CHAR);
return 'data:image/jpeg;base64,' + base64String;
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