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
  constructor(private route: ActivatedRoute, private authService: AuthService) { }


  selectFile(event : any) {
    this.selectedFiles = event.target.files[0];
  }


  onSubmit(): void {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
debugger;
    this.book = new book();
    this.authService.getBookById(this.id).subscribe( data => {
      this.book = data;
    });
  }
}
