package com.shop.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.shop.user.Model.Address;
import com.shop.user.Model.User;
import com.shop.user.control.UserControl;
import com.shop.user.service.UserService;

@WebMvcTest(value = UserControl.class)
class UserApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService service;//mocking the result in userservice

	User users;
	String name;//methods names given Ex-> testdeleteuser

	@BeforeAll
	static void beforeAll() {
		System.out.println("Running the test Cases");
	}

	@BeforeEach
	void BeforeEach() {
//user details provided => 1st before each gets craeted n after that all after methods
		Address address = new Address("89/2-2-11-3", "Majestic function hall line", "Balaji Nagar", "Kurnool",
				"Andhra Pradesh", 518006);
		users=new User("Kanshi123","Kanshi","kanshi@gmail.com","female","12-03-2000","admin"," 0987654321","Kanshi@1234",address);
		System.out.println("Test Case Started");
	}

	@AfterEach
	void afterEach() {
		System.out.println(name + " Runned Successfully");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("All test cases completed");
	}

	@Test
	@DisplayName("Get User")
	void testGetByUserName() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Mockito.when(service.getByUserName(users.getUserName())).thenReturn(users);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/user/" + users.getUserName()).contentType(MediaType.APPLICATION_JSON))//mocking the get requests
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))/* */
				.andExpect(jsonPath("$.userName", is(users.getUserName())));
	}

	@Test
	@DisplayName("Create User")
	void testCreateUser() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.createUser(users)).thenReturn(users);
		String bookJson = new ObjectMapper().writeValueAsString(users);
		mockMvc.perform(post("/user/new/add").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Update User")
	void testUpdateUser() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.getByUserName(users.getUserName())).thenReturn(users);
		when(service.updateUser(users)).thenReturn(users);
		String bookJson = new ObjectMapper().writeValueAsString(users);//json into java objects
		mockMvc.perform(
				put("/user/update/" + users.getUserName()).contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Delete User")
	void testDeleteUser() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.getByUserName("by2900")).thenReturn(users);
		when(service.deleteByUserName(users.getUserName())).thenReturn("user deleted");
		mockMvc.perform(delete("/user/delete/by2900").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

}
