package com.Book.repository;

import org.springframework.stereotype.Repository;

import com.Book.model.ERole;
import com.Book.model.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;



@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);

}
