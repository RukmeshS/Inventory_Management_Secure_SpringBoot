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

import com.ty.ims.inventory_prject_boot.dto.Customer;
import com.ty.ims.inventory_prject_boot.service.CustomerSevice;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerSevice service;

	@ApiOperation(value = "save Outward", notes = "method used to save Outward")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "notfound") })

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> saveOutward(@RequestBody Customer customer) {
		return service.saveOutward(customer);
	}

	@ApiOperation(value = "update Outward", notes = "method used to update Outward by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "notfound") })

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> updateOutward(@RequestBody Customer customer,
			@RequestParam String customerid, @RequestParam int itemId, @RequestParam int inventoryid) {
		return service.upadetOutward(customer, customerid, itemId, inventoryid);
	}

	@ApiOperation(value = "get Outward", notes = "method used to fetch Outward by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "notfound") })

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> getOutwardById(@RequestParam String id) {
		return service.getOutwardById(id);
	}

	@ApiOperation(value = "delete Outward", notes = "method used to delete Outward by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "notfound") })

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<ResponseStructure<Customer>> deleteOutwardById(@PathVariable String id) {
		return service.deleteOutwardById(id);
	}

}
