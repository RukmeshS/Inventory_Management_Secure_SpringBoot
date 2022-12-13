package com.ty.ims.inventory_prject_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.service.ItemService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("items")
public class ItemController {

	@Autowired
	ItemService service;

	@ApiOperation(value = "Create Item", notes = "Used in Creation of Items")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item, @RequestParam int id) {
		return service.serviceSaveItem(item, id);
	}

	@ApiOperation(value = "Updation of Item", notes = "Used in Updation of Items")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping(value = "/{inventoryID}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Item>> updateItem(@RequestBody Item item, @RequestParam int id,
			@PathVariable int inventoryID) {
		return service.serviceUpdateItem(item, id, inventoryID);
	}

	@ApiOperation(value = "Finding Item", notes = "Used to find Items by Id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Item>> findItembyid(@RequestParam int id) {
		return service.serviceFinditembyid(id);
	}

	@ApiOperation(value = "Deleting Item", notes = "Used to Delete Items by Id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Item>> deleteItembyid(@PathVariable int id) {
		return service.serviceDeleteItem(id);
	}

}
