package com.shop.order;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.shop.order.api.CartTemplate;
import com.shop.order.api.UserTemplate;
import com.shop.order.control.OrderController;
import com.shop.order.model.Address;
import com.shop.order.model.Cart;
import com.shop.order.model.Item;
import com.shop.order.model.Order;
import com.shop.order.model.Product;
import com.shop.order.model.User;
import com.shop.order.service.OrderService;

@WebMvcTest(value = OrderController.class)
class OrderApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	OrderService service;

	@MockBean
	CartTemplate fallBack;

	@MockBean
	UserTemplate userFallBack;

	Order order;
	User user;
	Address address;
	Cart cart;
	Product product;
	Item items;
	String name;

	@BeforeAll
	static void beforeAll() {
		System.out.println("Running the test Cases");
	}

	@BeforeEach
	void BeforeEach() {
		product = new Product("123", "TS Athiletic", 499.0);
		items = new Item(product, 1);
		List<Item> item = new ArrayList<Item>();
		item.add(items);
		cart = new Cart("ng2482", 499.0, item);
		Address address = new Address("89/2-2-11-3", "Majestic function hall line", "Balaji Nagar", "Kurnool",
				"Andhra Pradesh", 518006);
		user=new User("kanshi","Kankshini","kanshi@gmail.com","female","12-09-2000","user","1234567890","kanshi@123",address);
		order = new Order(cart.getCartId(), LocalDate.now(), cart.getTotalPrice(), "placed", address, "9515962633l",
				item);
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
	void testGetCartById() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();

		Mockito.when(service.getByOrderId("ng2482")).thenReturn(order);

		mockMvc.perform(MockMvcRequestBuilders.get("/order/getOrder/ng2482")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Create cart")
	void testAddItemToCart() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(fallBack.getCart(cart.getCartId())).thenReturn(cart);
		when(userFallBack.getUserInfo(cart.getCartId())).thenReturn(user);
		when(service.getByCustomerId(order.getCustomerId())).thenReturn(null);
		mockMvc.perform(post("/order/addOrder/ng2482")).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Update order")
	void testUpdateOrder() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.getByOrderId(order.getCustomerId())).thenReturn(order);
		mockMvc.perform(put("/order/updateOrder/ng2482")).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Delete Order")
	void testDeleteOrder() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.getByOrderId(cart.getCartId())).thenReturn(null);
		mockMvc.perform(delete("/order/deleteOrder/ng2482")).andExpect(status().isNoContent());
	}

}
