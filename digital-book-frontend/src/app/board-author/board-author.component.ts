import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-board-author',
  templateUrl: './board-author.component.html',
  styleUrls: ['./board-author.component.css']
})
export class BoardAuthorComponent implements OnInit {
  form: any = {
    booktitle: null,
    price: null,
    category:null,
    audiourl:null,
    content:null,
    publishdate:null
  };
  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  selectedFiles = File;
  selectedLogo = File;
  logo!: { new(fileBits: BlobPart[], fileName: string, options?: FilePropertyBag | undefined): File; prototype: File; };
  bookcode!: { new(fileBits: BlobPart[], fileName: string, options?: FilePropertyBag | undefined): File; prototype: File; };

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }
    
  selectFile(event : any) {
    this.selectedFiles = event.target.files[0];
  }

  selectLogo(event : any) {
    this.selectedLogo = event.target.files[0];
  }

  onSubmit(): void {
     this.bookcode= this.selectedFiles;
     this.logo = this.selectedLogo;
    const {  booktitle, price,category,audiourl,content,publishdate} = this.form;
    

    this.authService.createBook(booktitle,  this.bookcode,price,category,audiourl,content,this.logo,publishdate).subscribe(
      data => {
        this.isSuccessful = true;
        this.isFailed = false;
        setTimeout(() => {
          setTimeout(() => {
            window.location.href = location.origin+"/home";
          });
        }, 3000);
      },
      err => {
        if(err.status == 200){
          this.isSuccessful = true;
          this.isFailed = false;
          setTimeout(() => {
            setTimeout(() => {
              window.location.href = location.origin+"/home";
            });
          }, 3000);
        }else{
          this.errorMessage = err.error.message;
          this.isFailed = true;
        }
       
      }
    );
  }
}