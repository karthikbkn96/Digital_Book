import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { book } from '../book';

const AUTH_API = 'http://localhost:8087/api/auth/';
const BOOK_API = 'http://localhost:8087/digitalbook/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string, phonenumber: string, role: Array<String>): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password,
      phonenumber,
      role
    }, httpOptions);
  }

  createBook(booktitle : String,  bookcode :any,price:String,category:String,audiourl:String,content :String,logo:any): Observable<any> {
   const json = {booktitle,price,category,audiourl,content};
    
    const formData = new FormData();
    formData.append('logo', logo);
formData.append('bookcode', bookcode);
formData.append('book', new Blob([JSON.stringify(json)], {
      type: "application/json"
  }));
    
    return this.http.post(BOOK_API + 'createBookByAuthor',formData);
  }

  getBooksList(): Observable<book[]>{
    return this.http.get<book[]>(BOOK_API + 'AuthorBooks');
  }

  blockunblock(userid:number, blockorunblock: String): Observable<any> {
    return this.http.post(BOOK_API + 'blockUnBlockBook/'+userid,{blockorunblock});
   }

   getBookById(id: number): Observable<book>{
    return this.http.get<book>(BOOK_API + 'getbookById/'+id);
  }

  getSubscribedList(): Observable<book[]>{
    return this.http.get<book[]>(BOOK_API + 'SubscribedBooks');
  }
}