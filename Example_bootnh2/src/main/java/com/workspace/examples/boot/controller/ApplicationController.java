package com.workspace.examples.boot.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workspace.examples.boot.entity.Books;
import com.workspace.examples.boot.service.BookService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ApplicationController {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<Books> retrieveAllBookRecords() {
		return bookService.findAllBooks();
	}

	@GetMapping("/books/{id}")
	public Books retrieveBookbyId(@PathVariable long id) throws Exception {
	 Books book = bookService.findBooksById(id);

		if (book==null)
			throw new Exception("id-" + id);

		return book;
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBookRecord(@PathVariable long id) {
		bookService.deleteBooksbyId(id);
	}

	@PostMapping(path="/books")
	public ResponseEntity<Object> createBookRecord(@RequestBody Books book) {
		Books savedStudent = bookService.addBook(book);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/books/updateBulk")
	public ResponseEntity<Object> updateBookList(@RequestBody List<Books> bookList) {

		String bookIds=bookService.modifyBooks(bookList);

		log.info("Modified Book IDs:"+ bookIds);

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/books")
	public ResponseEntity<Object> updateBookRecord(@RequestBody Books book) {

		String bookIds=bookService.modifyBookSingle(book);

		log.info("Modified Book IDs:"+ bookIds);

		return ResponseEntity.noContent().build();
	}

}
