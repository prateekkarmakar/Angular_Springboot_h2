import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BooksListComponent } from './books-list/books-list.component';
import { BooksCreateComponent } from './books-create/books-create.component';


const routes: Routes = [{ path:  '', redirectTo:  'books', pathMatch:  'full' },
{
    path:  'books',
    component:  BooksListComponent
},
{
    path:  'books/add',
    component:  BooksCreateComponent
},
{
    path:  'books/edit/:id',
    component:  BooksCreateComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
