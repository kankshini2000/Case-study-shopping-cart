package com.shop.user.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.shop.user.Model.User;
import com.shop.user.exception.UserNotFound;
import com.shop.user.repo.UserRepo;
import com.shop.user.service.UserService;

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

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserControl {
    

    @Autowired
	private UserService userService;

    @GetMapping("/all")
	public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String fullName) throws Exception{
		try {
			List<User> userProfile = new ArrayList<User>();
			if (fullName == null)
            userService.getAll().forEach(userProfile::add);
			else
            userService.getByName(fullName);
			
			if (userProfile.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(userProfile, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// User get by username
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserById(
			 @PathVariable("username") String username) throws Exception {

		User userData = userService.getByUserName(username);
		if (userData == null) {
			throw new UserNotFound(username + " not found ");
		}
		return new ResponseEntity<>(userData, HttpStatus.OK);
	}

	// User update
	//@Operation(summary = "Update User Details")
	@PutMapping("/update/{username}")
	public ResponseEntity<User> updateUser(
			 @PathVariable("username") String username,
			@RequestBody User user) throws Exception {

		User userData = userService.getByUserName(username);
		if (userData != null) {
			User user2 = userService.createUser(user);
			return new ResponseEntity<>(user2, HttpStatus.CREATED);
		} else {
			throw new UserNotFound(username + " not found ");
		}
	}

	// User register
	//@Operation(summary = "Add new User")
	@PostMapping("/add")
	public ResponseEntity<User> createUser( @Valid @RequestBody User user) {
		User userData = userService.getByUserName(user.getUserName());
		user.setRole("user");
		if (userData == null) {
			User user2 = userService.createUser(user);
			return new ResponseEntity<>(user2, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
		}
	}

	// User delete
	//@Operation(summary = "Delete User")

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<HttpStatus> deleteUser(
			 @PathVariable("username") String username) throws Exception {
		User userData = userService.getByUserName(username);
		if (userData != null) {
			userService.deleteByUserName(username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new UserNotFound(username + " not found ");
		}
	}
}
