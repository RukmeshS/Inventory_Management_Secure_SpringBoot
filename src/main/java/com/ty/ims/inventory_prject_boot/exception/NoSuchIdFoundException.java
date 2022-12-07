package com.ty.ims.inventory_prject_boot.exception;

public class NoSuchIdFoundException extends RuntimeException {

	String message= "No Such ID Found In The DataBase";
	
	
	
	public NoSuchIdFoundException(String message) {
		super();
		this.message = message;
	}
	
	public NoSuchIdFoundException() {
		
	}

	@Override
	public String getMessage() {
		return message;
	}

	
}
