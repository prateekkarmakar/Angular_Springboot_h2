package com.workspace.examples.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Books {
	
	@Id
	@GeneratedValue
    private Long id;
    private String name;
    private String author;
    private String publisher;
    private Double price; 
    private String isbn;

    public Books() {
        super();
    }

    public Books(Long id, String name, String author,String publisher,Double price,String isbn ) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.isbn = isbn;
    }

    public Books(String name, String author,String publisher,Double price,String isbn ) {
        super();
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override

    public String toString() {
        return String.format("Book [id=%s, name=%s, author=%s, publisher=%s, price=%f, isbn=%s ]", id, name, author, publisher, price, isbn);
    } 

}
