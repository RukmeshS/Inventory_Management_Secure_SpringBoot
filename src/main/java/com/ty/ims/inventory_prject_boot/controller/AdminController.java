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

import com.ty.ims.inventory_prject_boot.dto.Admin;
import com.ty.ims.inventory_prject_boot.service.AdminService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;


	@ApiOperation(value = "Save Admin", notes = "Used to save the admin")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 302, message = "Found"),
			@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Internal Server Error")

	})
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@ApiOperation(value = "Get The Admin", notes = "Used To Get The Admin")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 302, message = "Found"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Admin>> getAdmin(@PathVariable int id) {
		return adminService.getAdminById(id);
	}

	@ApiOperation(value = "Admin Login", notes = "Used To Do Admin Login")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 302, message = "Found"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Admin>> loginAdmin(@RequestBody Admin admin) {
		return adminService.loginAdmin(admin);
	}

	@ApiOperation(value = "Update The Admin", notes = "Used To Update The Admin")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Found"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin, @RequestParam int id) {
		return adminService.updateAdmin(admin, id);
	}

	@ApiOperation(value = "Delete The Admin", notes = "Used To Delete The Admin")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Found"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@PathVariable int id) {
		return adminService.deleteAdmin(id);
	}
}
