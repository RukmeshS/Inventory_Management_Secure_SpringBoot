package com.ty.ims.inventory_prject_boot.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	String message= " No Customer Found ";
	
	
	@Override
	public String getMessage() {
		return message;
	}

	public CustomerNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	public CustomerNotFoundException() {
	
	}

}
