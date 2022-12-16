import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { book } from '../book';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';
import {ToastrModule, ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent {


bookCount: boolean = false;
    
      content?: string;
      Books: book[] | undefined;
      constructor(private userService: UserService,private toastr: ToastrService, private tokenStorage: TokenStorageService, private router: Router, private authService: AuthService) { }
    
      private getBooks(){
        this.authService.getSubscribedList().subscribe(data => {
          this.Books = data;
          if(data.length > 0){
            this.bookCount = true;
          }else{
            this.bookCount=false;
          }
        });
      }
    
      UnSubscribe(id: number,blockunblock: string) {
        this.authService.unSubscribe(id, blockunblock).subscribe(data => {
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