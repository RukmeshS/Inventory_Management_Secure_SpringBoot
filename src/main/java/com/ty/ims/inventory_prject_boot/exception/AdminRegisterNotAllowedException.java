package com.ty.ims.inventory_prject_boot.exception;

public class AdminRegisterNotAllowedException extends RuntimeException{

	
	String message = "You are not allowed to become admin";

	public AdminRegisterNotAllowedException(String message) {
		super();
		this.message = message;
	}
	
	public AdminRegisterNotAllowedException() {
		
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	

	
}
