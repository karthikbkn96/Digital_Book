import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { book } from '../book';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  id!: number;
  book!: book;
  isSuccessful = false;
  isFailed = false;
  errorMessage = '';
  selectedFiles = File;
  selectedLogo = File;
  logo!: { new(fileBits: BlobPart[], fileName: string, options?: FilePropertyBag | undefined): File; prototype: File; };
  bookcode!: { new(fileBits: BlobPart[], fileName: string, options?: FilePropertyBag | undefined): File; prototype: File; };
  
  form: any = {
    booktitle: null,
    price: null,
    category:null,
    audiourl:null,
    content:null,
    publishdate:null
  };
  constructor(private route: ActivatedRoute, private authService: AuthService) { }


  selectFile(event : any) {
    this.selectedFiles = event.target.files[0];
  }

  selectLogo(event : any) {
    this.selectedLogo = event.target.files[0];
  }

  onSubmit(): void {
    this.bookcode= this.selectedFiles;
    this.logo = this.selectedLogo;
   const { booktitle, price,category,audiourl,content,publishdate} = this.form;
   

   this.authService.updateBook(this.id,booktitle,  this.bookcode,price,category,audiourl,content,this.logo,publishdate).subscribe(
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

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.book = new book();
    this.authService.getBookById(this.id).subscribe( data => {
      this.book = data;
    });
  }
}
