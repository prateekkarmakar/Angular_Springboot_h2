package com.workspace.examples.boot.service;

import java.util.List;

import com.workspace.examples.boot.entity.Books;

public interface BookService {
	
	public String addBookReturnId(Books book);
	
	public Books addBook(Books book);
	
	public List<Books> findAllBooks();
	
	public Books findBooksById(long id);
	
	public List<Books> findBooksByName(String name);
	
	public String modifyBooks(List<Books> booklist);
	
	public String deleteBooksbyId(long id);

	public String modifyBookSingle(Books book);

}
