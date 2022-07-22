package com.shop.product;

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
import com.shop.product.control.AdminControl;
import com.shop.product.model.Product;
import com.shop.product.service.ProductService;

@WebMvcTest(value = AdminControl.class)
class ProductApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductService service;

	Product product;
	String name;

	@BeforeAll
	static void beforeAll() {
		System.out.println("Running the test Cases");
	}

	@BeforeEach
	void BeforeEach() {
		product = new Product("124", "Jorden-1", "Jorden-1", "SHoes", "abc.jpg", 199.0, 3.5,
				"Rubber shoes added");
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
	@DisplayName("Get Product")
	public void testGetProductByd() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Mockito.when(service.getById(product.getProductId())).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/product/admin/getproid/" + product.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(notNullValue())))
				.andExpect(jsonPath("$.productId", is(product.getProductId())));
	}

	@Test
	@DisplayName("Create Product")
	public void testCreateProduct() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.createProduct(product)).thenReturn(product);
		String bookJson = new ObjectMapper().writeValueAsString(product);
		mockMvc.perform(post("/product/admin/addpro").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Update Product")
	public void testUpdateProduct() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.getById(product.getProductId())).thenReturn(product);
		when(service.createProduct(product)).thenReturn(product);
		String bookJson = new ObjectMapper().writeValueAsString(product);
		mockMvc.perform(put("/product/admin/updatepro/" + product.getProductId()).contentType(MediaType.APPLICATION_JSON)
				.content(bookJson)).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Delete Product")
	public void testDeleteProduct() throws Exception {
		name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		when(service.getById(product.getProductId())).thenReturn(product);
		when(service.deleteById(product.getProductId())).thenReturn("product deleted");
		String bookJson = new ObjectMapper().writeValueAsString(product);
		mockMvc.perform(delete("/product/admin/deletepro/" + product.getProductId()).contentType(MediaType.APPLICATION_JSON)
				.content(bookJson)).andExpect(status().isNoContent());
	}


   


}
