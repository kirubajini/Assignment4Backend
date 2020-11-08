package com.Book.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.model.Book;
import com.Book.service.BookService;



@CrossOrigin("*")
@RestController
@RequestMapping ("/Book")
public class BookController {
	
	@Autowired
	BookService bookService;
	

	@GetMapping
	public ResponseEntity<List<Book>> getAllBook() {
		 return bookService.getAllBook ();
		
	}
	@PostMapping
	public ResponseEntity <Book> createBook (@RequestBody Book book){
	   return bookService.createBook(book);

     }
	@GetMapping("/{id}")
	  public  ResponseEntity <Book> getBookById(@PathVariable String id) {
		 return bookService.getBookById(id);
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity<Book> updateBookById(@RequestBody Book book, @PathVariable String id) {
		return bookService.updateBookById(id,book);
		 
	 }
	
		
	 @DeleteMapping("/{id}")
	 public ResponseEntity<HttpStatus>  deleteBookById(@PathVariable String id) {
		 return bookService.deleteBookById(id);
	 }
	 @GetMapping(value = "/page/serachedPages")
		public ResponseEntity<Map<String,Object>> getSerchedBook(
				@RequestParam(name = "searched",defaultValue = "null") String searched,
				@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
				@RequestParam(name = "pageSize",defaultValue = "5") int pageSize
				){
		   return bookService.searchedBook(searched,pageNo,pageSize);
	 }
			
      
	 
	 @GetMapping("/page")
	    public ResponseEntity<Map<String, Object>> getAllBookInPage(
	    		@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
	    		@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, 
	    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
			return bookService.getAllBookInPage(pageNo, pageSize, sortBy);
		}
	


	 
}

