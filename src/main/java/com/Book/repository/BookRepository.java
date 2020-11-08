package com.Book.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Book.model.Book;



@Repository
public interface BookRepository extends MongoRepository<Book,String>{
     
	@Query(" {$or:[{'Title' : {$regex: ?0, $options: 'i'}}, {'Author': {$regex: ?0, $options: 'i'}} ]}")
	List<Book> searchedBook(String searched);

//	List<Book> searchedBook(String searched);



	

   }


