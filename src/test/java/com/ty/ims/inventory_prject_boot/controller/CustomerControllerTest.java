package com.ty.ims.inventory_prject_boot.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.ty.ims.inventory_prject_boot.dto.Customer;
import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.service.CustomerSevice;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {
	
	@LocalServerPort
    private int port;
	
	@Autowired
	CustomerSevice customerSevice;
//
//	@Test
//	@Order(1)
//	@DisplayName("POST /Save Customer")
//	void testSaveOutward() throws Exception {
//		Customer customer = Customer.builder().customerEmailId("praveen@gmail.com").customerName("Praveen").customerPhoneNo(75646782).outwardQuantity(12).outwardDate(new Date()).build();
//		ResponseEntity<ResponseStructure<Customer>> customer2=customerSevice.saveOutward(customer);
//		org.assertj.core.api.Assertions.assertThat(customer2.getStatusCodeValue()).isEqualTo(201);
//	}	

//	@Test
//	@Order(2)
//	@DisplayName("PUT /Update Customer")
//	void testUpdateOutward() throws Exception{
//		Item item1= Item.builder().item_name("Coke Drink").item_price(20).item_quantity(12).build();
//		Item item2= Item.builder().item_name("Kurkure").item_price(30).item_quantity(10).build();
//
//		List<Item> items=Arrays.asList(item1,item2); 
//		Customer customer = new Customer("Usr_1","Praveen","praveen@gmail.com","75646782",68357357,new Date(),12,"USER",items);
//
//		TestRestTemplate testRestTemplate = new TestRestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		
//		 
//        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
//		ResponseEntity<String> response = testRestTemplate.
//		  postForEntity("http://localhost:"+port+"/customer?customerid=1&itemId=1&inventoryid=1",request, String.class);
//		 
//		org.assertj.core.api.Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
//	}

	@Test
	@Order(3)
	@DisplayName("GET /Customer By Id")
	public void testGetOutwardById() throws Exception {
		ResponseEntity<ResponseStructure<Customer>> response =customerSevice.getOutwardById("Usr_1"); 
		 
		org.assertj.core.api.Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(302);

		
	}

	@Test
	@Order(4)
	@DisplayName("DELETE /Delete Customer")
	void testDeleteOutwardById() {
		ResponseEntity<ResponseStructure<Customer>> customer= customerSevice.deleteOutwardById("Usr_1");		  
		
		org.assertj.core.api.Assertions.assertThat(customer.getStatusCodeValue()).isEqualTo(302);
	}

}
