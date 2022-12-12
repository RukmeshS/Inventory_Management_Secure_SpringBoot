package com.ty.ims.inventory_prject_boot.service;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.ty.ims.inventory_prject_boot.dto.EmailDetails;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

public interface EmailService {

	 abstract ResponseEntity<ResponseStructure<String>> sendSimpleMail(EmailDetails details);
	 
	    
	   abstract ResponseEntity<ResponseStructure<String>> sendMailWithAttachment(EmailDetails details) throws MessagingException;
}
