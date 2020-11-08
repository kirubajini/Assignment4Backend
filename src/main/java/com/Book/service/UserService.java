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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Book.model.Book;
import com.Book.model.User;
import com.Book.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	public ResponseEntity<List <User>> getAllUsers(){
		try {
			List<User> user= new ArrayList<User>();
			userRepository.findAll().forEach(user::add);
			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<> (user,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
//	public ResponseEntity<User> createUser(User user) {
//		try {
//			User pou = userRepository.insert(user);
//			return new ResponseEntity<>(pou,HttpStatus.CREATED);
//		 }catch (Exception e) {
//			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		 }
//	}
	
	public ResponseEntity<User> createUser(User user) {
	    try {
			User pou = userRepository.insert(user);
			return new ResponseEntity<>(pou,HttpStatus.CREATED);
		 }catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
}
	
	public ResponseEntity<User> getUserById(String id) {
		Optional<User> user =userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}


		
	

	public ResponseEntity<Map<String, Object>> getAllUserInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object>response=new HashMap<>();
			Sort sort=Sort.by(sortBy);
			Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
			Page<User> page=userRepository.findAll(pageable);
			response.put("data", page.getContent());
			response.put("Total no of pages", page.getTotalPages());
			response.put("Totalnoofelements", page.getTotalElements());
			response.put("Current page no", page.getNumber());
			
			return new ResponseEntity<>(response,HttpStatus.OK);
	    }catch(Exception e) {
	    	return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}


	public ResponseEntity<User> deleteUserById(String id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<List<User>> searchUser(String username){
		try {
			List<User> user = userRepository.findByUsernameContaining(username);
			
			if(user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(user,HttpStatus.OK);
		    }catch (Exception e) {
		    	return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		    }
		}

	public ResponseEntity<User> updateUserById(User user, String id) {
		Optional<User> oldUser = userRepository.findById(id);

		try {
		if (oldUser.isPresent()) {
		User _user = oldUser.get();

		// _user.setUsername(user.getUsername());
		// _user.setEmail(user.getEmail());
		_user.setRoles(user.getRoles());
//		_user.setFullname(user.getFullname());
//		_user.setDistrict(user.getDistrict());
//		_user.setPhonenumber(user.getPhonenumber());
//		_user.setCity(user.getCity());
//		_user.setAddress(user.getAddress());





		// _user.setPassword(newUser.getPassword());


		return new ResponseEntity<> (userRepository.save(_user),HttpStatus.OK);
		} else {
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		}catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


		}

	public ResponseEntity<User> updateEmail(String id, String email) {
		try {
			Optional<User> user=userRepository.findById(id);
			if(user.isPresent()) {
				User _user = user.get();
				if(_user.getEmail().equals(email)) {
					return new ResponseEntity<> (_user,HttpStatus.OK);
				}else if(userRepository.existsByEmail(email)) {
					return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
				}
				else {
					_user.setEmail(email);
					userRepository.save(_user);
					return new ResponseEntity<> (_user,HttpStatus.OK);
				}
			}else {
				return new ResponseEntity<> (HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	public ResponseEntity<Map<String, Object>> searchUser(String searched, int pageNo, int pageSize) {
		List<User> searchUser = userRepository.searchUser(searched);
		Map<String, Object> response = new HashMap<>();
		PagedListHolder<User> page = new PagedListHolder<User>(searchUser);
		page.setPageSize(pageSize); // number of items per page
		page.setPage(pageNo); 
		
		response.put("data", page.getPageList());
		response.put("Total_No_Of_Elements", page.getNrOfElements());
		response.put("TotalNoOfPages", page.getPage());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	
//	public ResponseEntity<User> updateFullname(String id, String name) {
//		Optional<User> user=userRepository.findById(id);
//		
//		if(user.isPresent()) {
//			User _user = user.get();
//			_user.setFullname(name);
//			userRepository.save(_user);
//			return new ResponseEntity<> (_user,HttpStatus.OK);
//		}else {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//	}

//	public ResponseEntity<User> updatePhoneNumber(String id, String phonenumber) {
//		Optional<User> user=userRepository.findById(id);
//		
//		if(user.isPresent()) {
//			User _user = user.get();
//			_user.setPhonenumber(phonenumber);
//			userRepository.save(_user);
//			return new ResponseEntity<> (_user,HttpStatus.OK);
//		}else {
//			
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//	}
		
	}

	



		

	
