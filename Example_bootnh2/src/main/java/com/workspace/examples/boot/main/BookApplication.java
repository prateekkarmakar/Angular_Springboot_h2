package com.workspace.examples.boot.main;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.workspace.examples.boot.entity.Books;
import com.workspace.examples.boot.entity.BooksRepository;


@EnableJpaRepositories(basePackages ="com.workspace.examples.boot.entity")
@EntityScan(basePackages = "com.workspace.examples.boot.entity")
@ComponentScan(basePackages = {"com.workspace.examples.boot"})
@SpringBootApplication
public class BookApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(BookApplication.class);
	
	@Autowired
	private BooksRepository booksRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		Books book = new Books("Qill", "Admin","AdminPub",23.42d,"ISN-224C");
		booksRepository.save(book);
		log.info("New User is created : " + book);
		
		book = new Books("JBill", "IT","AdminPub",24.42d,"ISN-225C");
		booksRepository.save(book);
		log.info("New User is created : " + book);
		
		book = new Books("PBXXXXXXl", "IT2","AdminCod",25.42d,"ISN-205C");
		booksRepository.save(book);
		log.info("New User is created : " + book);
		
		Optional<Books> booksWithId = booksRepository.findById(2L);
		log.info("Book is retrived : " + (booksWithId.isPresent()==true?booksWithId.get().getIsbn():"Soory!no Result"));
		
		booksWithId = booksRepository.findById(4L);
		log.info("Book is retrived : " + (booksWithId.isPresent()==true?booksWithId.get().getIsbn():"Soory!no Result"));

		List<Books> bookslist = booksRepository.findAll();
		log.info("All Books count : " + bookslist.size());			
		
		List<Books> booksWithName = booksRepository.getBookNamesLike("J");
		log.info("Book is retrived : " + (booksWithName!=null?booksWithName.size():"Soory!no Result"));
		
		/*booksRepository.deleteById(5L);
		bookslist = booksRepository.findAll();
		log.info("All Books count after delete : " + bookslist.size());*/
		
		booksWithId = booksRepository.findById(3L);
		log.info(" Book Details : "+booksWithId.get().toString());
		Books modBook=booksWithId.get();
		modBook.setAuthor("Prateek");
		booksRepository.save(modBook);
		booksWithId = booksRepository.findById(3L);
		log.info(" Book Details after modify : "+booksWithId.get().toString());
	}
}
