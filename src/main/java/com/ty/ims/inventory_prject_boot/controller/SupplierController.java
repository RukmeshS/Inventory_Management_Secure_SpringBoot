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

import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.service.SupplierService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("supplier")
public class SupplierController {
	@Autowired
	private SupplierService service;

	@ApiOperation(value = "save Inward", notes = "method used to save Inward")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "found") })

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Supplier>> saveInward(@RequestBody Supplier supplier) {
		return service.saveinward(supplier);
	}

	@ApiOperation(value = "update Inward", notes = "method used to update Inward by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "found") })

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Supplier>> updateInward(@RequestBody Supplier supplier,
			@RequestParam int supplierid, @RequestParam int itemId, @RequestParam int inventoryid) {
		return service.updateinward(supplier,supplierid, itemId, inventoryid);
	}

	@ApiOperation(value = "get Inward", notes = "method used to fetch Inward by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "found") })

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Supplier>> getInwardById(@RequestParam int id) {
		return service.getInwardById(id);
	}

	@ApiOperation(value = "delete Inward", notes = "method used to delete Inward by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "created"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 500, message = "internal server error"), @ApiResponse(code = 404, message = "notfound"),
			@ApiResponse(code = 302, message = "found") })

	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Supplier>> deleteInwardById(@PathVariable int id) {
		return service.deleteInwardById(id);
	}
}
