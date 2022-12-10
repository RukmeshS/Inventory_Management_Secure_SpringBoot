package com.ty.ims.inventory_prject_boot.exception;

public class EmailNotSendException extends RuntimeException {
	
	String message="Error while Sending Mail";

	@Override
	public String getMessage() {
		return message;
	}

	public EmailNotSendException(String message) {
		super();
		this.message = message;
	}
	
	
	public EmailNotSendException() {
	
	}
		
}
