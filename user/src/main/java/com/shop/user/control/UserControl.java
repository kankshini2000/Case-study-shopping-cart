package com.shop.user.control;



import javax.validation.Valid;

import com.shop.user.Model.User;
import com.shop.user.exception.UserNotFound;
import com.shop.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserControl {
    
	Logger logger=LoggerFactory.getLogger(UserControl.class);

    @Autowired
	private UserService userService;


    // User get by username
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserById( @PathVariable("username") String username) throws Exception {

		User userData = userService.getByUserName(username);
		logger.trace("Shows users by username");
		if (userData == null) {
			throw new UserNotFound(username + " not found ");
		}
		return new ResponseEntity<>(userData, HttpStatus.OK);
	}

	// User update
	@PutMapping("/update/{username}")
	public ResponseEntity<User> updateUser(
			 @PathVariable("username") String username,
			 @RequestBody User user) throws Exception {

		User userData = userService.getByUserName(username);
		logger.trace("User updation done by username");
		if (userData != null) {
			user.setUserName(username);
			user.setPassword(userData.getPassword());
			User user2 = userService.updateUser(user);
			return new ResponseEntity<>(user2, HttpStatus.CREATED);
		} else {
			throw new UserNotFound(username + " not found ");
		}
	}

	// User register
	@PostMapping("/new/add")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User userData = userService.getByUserName(user.getUserName());
		user.setRole("user");
		System.out.println("user reoprtered");
		user.setPassword(user.getPassword());
		if (userData == null) {
			User user2 = userService.createUser(user);
			return new ResponseEntity<>(user2, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
		}
	}

	// User delete
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable("username") String username) throws Exception {
		User userData = userService.getByUserName(username);
		logger.trace("User deleted by its username");
		if (userData != null) {
			userService.deleteByUserName(username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new UserNotFound(username + " not found ");
		}
	}
}
