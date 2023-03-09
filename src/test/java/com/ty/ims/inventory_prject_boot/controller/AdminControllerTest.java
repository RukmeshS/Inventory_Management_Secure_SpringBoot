package com.ty.ims.inventory_prject_boot.controller;

import org.junit.jupiter.api.Assertions;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ty.ims.inventory_prject_boot.dto.Admin;
import com.ty.ims.inventory_prject_boot.service.AdminService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminControllerTest {
	
	@LocalServerPort
    private int port;

	
	@Autowired
	 AdminService adminService;
	
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	@Order(1)
	@DisplayName("GET /admin Found")
	public void testGetAdminById() throws Exception {
					
			TestRestTemplate testRestTemplate = new TestRestTemplate();
			ResponseEntity<String> response = testRestTemplate.
			  getForEntity("http://localhost:"+port+"/admin "+ "/12", String.class);
			 
			Assertions.assertEquals(response.getStatusCode(), HttpStatus.FOUND);

			
			
	}
	
	@Test
	@Order(2)
	@DisplayName("SAVE /Save admin")
	public void testSaveAdmin() throws Exception {
		Admin admin= new Admin(14,"anmol.bag14@gmail.com","111","Anmol","admin role",95346868);

		TestRestTemplate testRestTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		 
        HttpEntity<Admin> request = new HttpEntity<>(admin, headers);
		ResponseEntity<String> response = testRestTemplate.
		  postForEntity("http://localhost:"+port+"/admin",request, String.class);
		 
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	@Order(3)
	@DisplayName("DELETE /Delete admin")
	public void testDeleteAdminById() {

		ResponseEntity<ResponseStructure<Admin>> admin= adminService.deleteAdmin(11);		  
		
		org.assertj.core.api.Assertions.assertThat(admin.getStatusCodeValue()).isEqualTo(302);

		
	}
	
	
		
	@Test
	@Order(4)
	@DisplayName("Update /Update admin")
	public void testUpdateAdmin() throws Exception {
		Admin admin= new Admin(12,"anmol.bag14@gmail.com","111","Anmol Singh","admin role",95346868);

		TestRestTemplate testRestTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		 
        HttpEntity<Admin> request = new HttpEntity<>(admin, headers);
		ResponseEntity<String> response = testRestTemplate.
		  postForEntity("http://localhost:"+port+"/admin?id=12",request, String.class);
		 
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
}
