import { Component, OnInit } from '@angular/core';
import { ApiService } from  '../api.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  private  books:  Array<object> = [];
  constructor(private  apiService:  ApiService,private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.getBookList();
  }

  public  getBookList(){
    this.apiService.getBookList().subscribe((data:  Array<object>) => {
        this.books  =  data;
        console.log(data);
    });
}

public editBookDetails(selectedItem: any){
  console.log("--------------------------------------------");
  console.log("Selected item Id: ", selectedItem.id);
  console.log("Selected item Name: ", selectedItem.name);
  console.log("--------------------------------------------");
  this.gotoEditBooks(selectedItem.id);
}

public deleteBookRecord(selectedItem: any){
  console.log("--------------------------------------------");
  console.log("Selected item Id: ", selectedItem.id);
  console.log("Selected item Name: ", selectedItem.name);
  console.log("--------------------------------------------");
  this.apiService.deleteBooksById(selectedItem.id).subscribe((response) => {
    console.log(response);
});
this.getBookList();
}

gotoEditBooks(bookId) {
  this.router.navigate(['/books/edit/'+bookId]);
}

}
