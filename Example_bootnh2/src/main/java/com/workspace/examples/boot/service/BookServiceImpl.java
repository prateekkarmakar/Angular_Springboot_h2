package com.workspace.examples.boot.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.workspace.examples.boot.entity.Books;
import com.workspace.examples.boot.entity.BooksRepository;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BooksRepository booksRepository;

	@Override
	public List<Books> findAllBooks() {
		List<Books> bookList=booksRepository.findAll();
		return bookList;
	}

	@Override
	public Books findBooksById(long id) {
		Optional<Books> booksWithId = booksRepository.findById(id);
		log.info("Book is retrived : " + (booksWithId.isPresent()==true?booksWithId.get().getIsbn():"Soory!no Result"));
		Books foundBooks=null; 
		if(booksWithId.isPresent()==true){
			foundBooks=booksWithId.get();
		}
		return foundBooks;
	}

	@Override
	public List<Books> findBooksByName(String name) {
		List<Books> booksWithName = booksRepository.getBookNamesLike("J");
		log.info("Book is retrived : " + (booksWithName!=null?booksWithName.size():"Soory!no Result"));
		return booksWithName;
	}

	@Override
	public String modifyBooks(List<Books> booklist) {
		
		StringBuilder sb=new StringBuilder();
		sb.append("The Ids of Books Modified : ");
		
		for(Books book: booklist){
			log.info("Details :"+book.toString());
			Books temp=booksRepository.save(book);
			sb.append(temp.getId()+",");
		}
		
		return sb.toString();
	}
	
	@Override
	public String modifyBookSingle(Books book) {
		
		StringBuilder sb=new StringBuilder();
		sb.append("The Ids of Books Modified : ");
		
			log.info("Details :"+book.toString());
			Books temp=booksRepository.save(book);
			sb.append(temp.getId()+",");
		
		return sb.toString();
	}

	@Override
	public String deleteBooksbyId(long id) {
		booksRepository.deleteById(id);
		return id+"";
	}

	@Override
	public String addBookReturnId(Books book) {
		Books addedBook=booksRepository.save(book);
		return addedBook.getId().toString();
	}

	@Override
	public Books addBook(Books book) {
		Books addedBook=booksRepository.save(book);
		return addedBook;
	}

}
