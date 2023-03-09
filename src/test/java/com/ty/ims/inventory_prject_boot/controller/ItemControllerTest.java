package com.ty.ims.inventory_prject_boot.controller;


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

import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.repository.ItemRepository;
import com.ty.ims.inventory_prject_boot.service.ItemService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemServiceTest {
	
	@Autowired
	ItemRepository repository;
	
	@Autowired
	ItemService service;
	
	@Test
	@Order(1)
	@DisplayName("POST / Save Items")
	void testServiceSaveItem() {
		
		Item item = Item.builder().item_name("sandal").item_price(15).item_quantity(10).build();
		ResponseEntity<ResponseStructure<Item>> items=service.serviceSaveItem(item, 2);;
		org.assertj.core.api.Assertions.assertThat(items.getStatusCodeValue()).isEqualTo(201);
				
	}

	@Test
	@Order(2)
	@DisplayName("PUT / Update Items")
	void testServiceUpdateItem() {
		
		Item item = Item.builder().item_name("sandal").item_price(15).item_quantity(10).build();
		ResponseEntity<ResponseStructure<Item>> item2 = service.serviceUpdateItem(item, 1, 2);
		Assertions.assertThat(item2.getStatusCodeValue()).isEqualTo(202);
	}

	@Test
	@Order(3)
	@DisplayName("GET / Find Item By ID")
	void testServiceFinditembyid() {

		ResponseEntity<ResponseStructure<Item>> item = service.serviceFinditembyid(1);
		Assertions.assertThat(item.getStatusCodeValue()).isEqualTo(302);
	}

	@Test
	@Order(4)
	@DisplayName("DELETE / Delete Item")
	void testServiceDeleteItem() {
		ResponseEntity<ResponseStructure<Item>> item = service.serviceDeleteItem(1);
		Assertions.assertThat(item.getStatusCodeValue()).isEqualTo(302);
	}

}
