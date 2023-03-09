package com.ty.ims.inventory_prject_boot.controller;


import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.repository.SupplierRepository;
import com.ty.ims.inventory_prject_boot.service.SupplierService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierServiceTest {

	@Autowired
	SupplierRepository repo;
	
	@Autowired
	SupplierService service;
	
	
	@Test
	@Order(1)
	@DisplayName("POST /Save Inward")
	void testSaveinward() {
		
		Supplier supplier = Supplier.builder().supplierName("Supplier1").supplierPhoneNo(123467).supplierEmailId("Supmail").inwardQuantity(10).inwardDate(new Date()).build();
		ResponseEntity<ResponseStructure<Supplier>> supplier2= service.saveinward(supplier);
		Assertions.assertThat(supplier2.getStatusCodeValue()).isEqualTo(201);
	}
	
	@Test
	@Order(2)
	@DisplayName("PUT /update Inward")
	void testUpdateinward() {
		
		Supplier supplier = Supplier.builder().supplierName("Supplier1").supplierPhoneNo(123467).supplierEmailId("Supmail").inwardQuantity(10).inwardDate(new Date()).build();
		ResponseEntity<ResponseStructure<Supplier>> supplier2 = service.updateinward(supplier, 1, 1, 1);
		Assertions.assertThat(supplier2.getStatusCodeValue()).isEqualTo(201);
		
	}

	@Test
	@Order(3)
	@DisplayName("GET /find Inward")
	void testGetInwardById() {
		Supplier supplier = repo.findById(1).get();
		Assertions.assertThat(supplier.getSupplierId()).isEqualTo(1);

	}

	@Test
	@Order(4)
	@DisplayName("DELETE /delete Inward")
	void testDeleteInwardById() {
		ResponseEntity<ResponseStructure<Supplier>> supplier = service.deleteInwardById(1);
		Assertions.assertThat(supplier.getStatusCode()).isEqualTo(302);
	}

}
