import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Book } from "../shared/book.model";

@Component({
    selector: 'app-books-create',
    templateUrl: './books-create.component.html',
    styleUrls: ['./books-create.component.css']
})
export class BooksCreateComponent implements OnInit {

    private bookData: Book;

    panelTitle: string;
    editButton: boolean;

    bookCreateForm = new FormGroup({
        name: new FormControl(''),
        author: new FormControl(''),
        publisher: new FormControl(''),
        price: new FormControl(''),
        isbn: new FormControl(''),
        id: new FormControl('')
    });

    constructor(private apiService: ApiService, private route: ActivatedRoute,
        private router: Router) { }

    ngOnInit() {

        console.warn(this.route.snapshot.url[1]);
        if (this.route.snapshot.url[1].toString() === 'edit') {
            this.route.paramMap.subscribe(parameterMap => {
                const bookid = +parameterMap.get('id');
                this.apiService.getBookById(bookid).subscribe((data) => {
                    let temp = JSON.stringify(data);
                    console.warn("Received Data : " + temp);
                    this.bookData = JSON.parse(temp);
                    this.bookCreateForm.setValue({
                        name: this.bookData.name,
                        author: this.bookData.author,
                        publisher: this.bookData.publisher,
                        price: this.bookData.price,
                        isbn: this.bookData.isbn,
                        id: this.bookData.id
                    });
                });
            });
            this.panelTitle = "Edit";
            this.editButton=true;
        } else {
            this.panelTitle = "Add";
            this.editButton=false;
        }

    }
    createUpdateBookList() {

        console.warn(this.bookCreateForm.value);
        var book = this.bookCreateForm.value;

        if (this.panelTitle === "Add") {
            this.apiService.createBookList(book).subscribe((response) => {
                console.log(response);
            });
        } else {
            this.apiService.updateBookList(book).subscribe((response) => {
                console.log(response);
            });
        }
        this.returntoBooks();
    }

    returntoBooks() {
        this.router.navigate(['/books']);
      }

}
