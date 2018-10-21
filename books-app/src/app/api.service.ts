import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  API_URL = 'http://localhost:8080';
  constructor(private httpClient: HttpClient) { }
  getBookList() {
    return this.httpClient.get(`${this.API_URL}/books`);
  }
  getBookById(bookid) {
    return this.httpClient.get(`${this.API_URL}/books/`+bookid);
  }
  createBookList(book) {
    return this.httpClient.post(`${this.API_URL}/books`, book);
  }
  updateBookList(book) {
    return this.httpClient.put(`${this.API_URL}/books`, book);
  }
  deleteBooksById(bookid) {
    return this.httpClient.delete(`${this.API_URL}/books/`+ bookid);
  }
}
