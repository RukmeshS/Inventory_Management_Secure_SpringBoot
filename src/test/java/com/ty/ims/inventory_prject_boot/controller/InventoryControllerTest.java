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
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.service.InventoryService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryControllerTest {

	@LocalServerPort
    private int port;
	
	@Autowired
	InventoryService inventoryService;
	
	@Test
	@Order(1)
	@DisplayName("POST /save Inventory")
	void testSaveInventory() throws Exception{
		Inventory inventory = Inventory.builder().product_name("Soap").build();
		ResponseEntity<ResponseStructure<Inventory>> inventory2=inventoryService.serviceSaveInventory(inventory);
		org.assertj.core.api.Assertions.assertThat(inventory2.getStatusCodeValue()).isEqualTo(201);
		
	}

	@Test
	@Order(2)
	@DisplayName("GET /Update Inventory Qty")
	void testUpdateInventoryQTY() throws Exception {
		Inventory inventory = Inventory.builder().product_id(1).product_name("Bottle Juice").build();
		ResponseEntity<ResponseStructure<Inventory>> inventory2=inventoryService.serviceqtySaveInventory(inventory,1);
		Assertions.assertThat(inventory2.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	@Order(3)
	@DisplayName("PUT /Update Inventory")
	void testUpdateInventory() throws Exception {
		Inventory inventory = Inventory.builder().product_name("Bottle Juice").build();
		ResponseEntity<ResponseStructure<Inventory>> inventory2=inventoryService.serviceUpdateInventory(inventory, 1);
		org.assertj.core.api.Assertions.assertThat(inventory2.getStatusCodeValue()).isEqualTo(202);
	}

	@Test
	@Order(4)
	@DisplayName("GET /Find Inventory By ID")
	void testFindInventorybyid() {
		ResponseEntity<ResponseStructure<Inventory>> response =inventoryService.serviceFindInvById(1); 
		 
		org.assertj.core.api.Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(302);
	}

	@Test
	@Order(5)
	@DisplayName("DELETE /Delete Inventory")
	void testDeleteInventorybyid() {
		ResponseEntity<ResponseStructure<Inventory>> response= inventoryService.serviceDeleteInventory(2);
		
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

}
