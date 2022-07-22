package com.shop.user.repo;

import java.util.List;

import com.shop.user.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String>{

    List<User> findByFullName(String fullName);

	User findByUserName(String userName);

	User findByEmail(String username);

	List<User> findByRole(String role);
}
