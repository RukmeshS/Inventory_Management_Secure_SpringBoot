package com.ty.ims.inventory_prject_boot.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ims.inventory_prject_boot.dto.EmailDetails;
import com.ty.ims.inventory_prject_boot.service.EmailService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("sendMail")
	public ResponseEntity<ResponseStructure<String>> sendMail(@RequestBody EmailDetails details) {

		return emailService.sendSimpleMail(details);
	}

	@PostMapping("/sendMailWithAttachment")
	public ResponseEntity<ResponseStructure<String>> sendMailWithAttachment(@RequestBody EmailDetails details)
			throws MessagingException {

		return emailService.sendMailWithAttachment(details);
	}
}
