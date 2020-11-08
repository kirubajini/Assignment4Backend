package com.Book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
public class Book {
	 
	@Id
	private String id;
    private String Title;
    private String Author;
    private String Url;
	private String Isbn;
	private String Price;
	private String Language;
    private String Genre;
	  
    
    public Book(String id, String Title, String Author, String Url, String Isbn, String Price, String Language,
     String Genre) {
		super();
		this.id = id;
		this.Title = Title;
		this.Author = Author;
		this.Url = Url;
		this.Isbn = Isbn;
		this.Price = Price;
		this.Language = Language;
		this.Genre = Genre;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		this.Title = Title;
	}


	public String getAuthor() {
		return Author;
	}


	public void setAuthor(String author) {
		this.Author = Author;
	}


	public String getUrl() {
		return Url;
	}


	public void setUrl(String url) {
		this.Url = Url;
	}


	public String getIsbn() {
		return Isbn;
	}


	public void setIsbn(String isbn) {
		this.Isbn = Isbn;
	}


	public String getPrice() {
		return Price;
	}


	public void setPrice(String price) {
		this.Price = Price;
	}


	public String getLanguage() {
		return Language;
	}


	public void setLanguage(String language) {
		this.Language = Language;
	}


	public String getGenre() {
		return Genre;
	}


	public void setGenre(String genre) {
		this.Genre = Genre;
	}

    

	
    
	
	
	
	
	
}


