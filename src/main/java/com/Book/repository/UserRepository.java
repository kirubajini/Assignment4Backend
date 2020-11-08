package com.Book.repository;

import org.springframework.stereotype.Repository;

import com.Book.model.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
    
	List<User> findByUsernameContaining(String username);
   
	@Query("{$or: [ { 'username': { $regex: ?0 , $options: 'i' } }, { 'email':{ $regex: ?0, $options: 'i' } },{ 'phonenumber': { $regex: ?0 , $options: 'i' } },{ 'fullname': { $regex: ?0 , $options: 'i' } } ,{ 'district': { $regex: ?0 , $options: 'i' } },{ 'city': { $regex: ?0 , $options: 'i' } },{ 'address': { $regex: ?0 , $options: 'i' } }]}")
	List<User> searchUser(String searched);


}
