package com.Book.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Book.model.Book;
import com.Book.repository.BookRepository;











@Service
public class BookService {
	
	
	@Autowired
	BookRepository	bookRepository;
	
	
	

	public ResponseEntity<List<Book>> getAllBook () {
		try {
			List<Book> book= bookRepository.findAll();
			if (book.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<> (book,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	public ResponseEntity<Book> createBook(Book book) {
	    try {
			Book pou = bookRepository.insert(book);
			return new ResponseEntity<>(pou,HttpStatus.CREATED);
		 }catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
}
	public ResponseEntity <Book> getBookById(String id) {
		Optional<Book> book =bookRepository.findById(id);
		if (book.isPresent()) {
			return new ResponseEntity<>(book.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<HttpStatus>  deleteBookById(String id) {
		try {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	public ResponseEntity<Map<String, Object>> getAllBookInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		    Page<Book> page = bookRepository.findAll(pageable);
		    response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("TotalNoOfElements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Book> updateBookById(String id, Book book) {
		Optional<Book> oldBook = bookRepository.findById(id);
		if (oldBook.isPresent()) {
			Book _book = oldBook.get();
			_book.setTitle(book.getTitle());
			_book.setAuthor(book.getAuthor());
			_book.setLanguage (book.getLanguage());
			_book.setUrl(book.getUrl());
			_book.setIsbn(book.getIsbn());
			_book.setPrice (book.getPrice());
			_book.setGenre(book.getGenre());
			
			return new ResponseEntity<> (bookRepository.save(book),HttpStatus.OK);
			}else {
				return new ResponseEntity<> (HttpStatus.NOT_FOUND);
			}
	}
	public ResponseEntity<Map<String, Object>> searchedBook(String searched, int pageNo, int pageSize) {
		List<Book> searchedBook = bookRepository.searchedBook(searched);
		Map<String, Object> response = new HashMap<>();
		PagedListHolder<Book> page = new PagedListHolder<Book>(searchedBook);
		page.setPageSize(pageSize); // number of items per page
		page.setPage(pageNo); 
		
		response.put("data", page.getPageList());
		response.put("Total_No_Of_Elements", page.getNrOfElements());
		response.put("TotalNoOfPages", page.getPage());
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

}
