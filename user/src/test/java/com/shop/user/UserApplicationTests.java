package com.shop.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.shop.user.Model.User;
import com.shop.user.repo.UserRepo;
import com.shop.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;


	@Test
	public void addProfileTest() {
		User user = new User("vaishnavi", "vs@gmail.com", "vs@gmail.com", "female", "1/5/2000", "user", "1234567890", "abc", null);
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
	}


	@Test
	public void deleteByIdTest() {
		String username="vaishnavi";
		userService.deleteByUserName(username);
		verify(userRepo,times(1)).deleteById(username);
	}

	@Test
	public void updateUsersTest() {
		String username="vaishnavi";
		User user = new User("vaishnavi", "vs@gmail.com", "vs@gmail.com", "female", "1/5/2000", "user", "1234567890", "abc", null);
		userService.updateUser(user);
		verify(userRepo, times(1)).save(user);
	}
	
	@Test
	public void getProfiles() {
		when(userRepo.findAll()).thenReturn(Stream.of(new User("vaishnavi", "vs@gmail.com", "vs@gmail.com", "female", "1/5/2000", "user", "1234567890", "abc", null)).collect(Collectors.toList()));
		assertEquals(1, userService.getAll().size());
	}

	

}
