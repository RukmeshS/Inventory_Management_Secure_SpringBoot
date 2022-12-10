package com.ty.ims.inventory_prject_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.service.InventoryService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("inventory")
public class InventoryController {

	@Autowired
	InventoryService service;

	@ApiOperation(value = "Create Inventory Product", notes = "Used in Creation of Inventory Products")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Inventory>> saveInventory(@RequestBody Inventory inventory) {
		return service.serviceSaveInventory(inventory);
	}

	@ApiOperation(value = "Updated Inventory Product with Quantity", notes = "Used in updation of Inventory Products Quantity")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Inventory>> updateInventoryQTY(@RequestBody Inventory inventory,
			@RequestParam int id) {
		return service.serviceqtySaveInventory(inventory, id);
	}

	@ApiOperation(value = "Updation of Inventory Product", notes = "Used in Updation of Inventory Products")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Inventory>> updateInventory(@RequestBody Inventory inventory,
			@RequestParam int id) {
		return service.serviceUpdateInventory(inventory, id);
	}

	@ApiOperation(value = "Finding Inventory Product", notes = "Used to find Inventory Products by Id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Inventory>> findInventorybyid(@RequestParam int id) {
		return service.serviceFindInvById(id);
	}

	@ApiOperation(value = "Deleting Inventory Product", notes = "Used to Delete Inventory Products by Id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 302, message = "Found") })
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Inventory>> deleteInventorybyid(@PathVariable int id) {
		return service.serviceDeleteInventory(id);
	}

}
