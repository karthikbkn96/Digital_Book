import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { book } from '../book';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent {


  form: any = {
    booktitle: "",
    author: "",
    releaseddate:""
  };
  errorMessage = '';
  searchSuccess=false;
  searchFailed=false;
  bookCount=false;
  booktitle="";
  author="";
  releaseddate="";
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    
  }
  Books!: book[];
  
  onSubmit():void {
    this.booktitle=this.form.booktitle;
    this.author=this.form.author;
    this.releaseddate=this.form.releaseddate;

    this.search();
  }

  
  subscribe(id: number,blockunblock: string) {
    this.authService.unSubscribe(id, blockunblock).subscribe(data => {
      this.onSubmit();
    }
    ,
      err => {
        if(err.status == 200){
          setTimeout(() => {
            setTimeout(() => {
              this.onSubmit();
            });
          }, 3000);
        }
        this.onSubmit();
      });
    
  }

private search(){
  const {booktitle,author,releaseddate} = this.form;

  this.authService.searchBook(booktitle,author,releaseddate).subscribe(
    data => {
      this.Books=data;
      this.searchFailed=false;
      this.searchSuccess=true;
      if(data.length >0){
       
        this.bookCount = true;
      }else{
        this.bookCount = false;
      }
    
      
    },
    err => {
      this.searchFailed=true;
      this.errorMessage = "Server Side Error";
    }
  );
}

}
