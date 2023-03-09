package com.ty.ims.inventory_prject_boot.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dto.EmailDetails;
import com.ty.ims.inventory_prject_boot.exception.EmailNotSendException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") 
	private String sender;
	

	@Override
	public ResponseEntity<ResponseStructure<String>> sendSimpleMail(EmailDetails details) throws EmailNotSendException  {
	
			 
            SimpleMailMessage mailMessage = new SimpleMailMessage();
 
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            
            ResponseStructure<String> responseStructure= new ResponseStructure<String>();
            
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Mail Sent Successfully...");
            responseStructure.setData("Mail Sent");
            
            javaMailSender.send(mailMessage);
            return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
      
 
     
		
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> sendMailWithAttachment(EmailDetails details)throws EmailNotSendException, MessagingException {
		
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
         mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
         mimeMessageHelper.setFrom(sender);
         mimeMessageHelper.setTo(details.getRecipient());
         mimeMessageHelper.setText(details.getMsgBody());
         mimeMessageHelper.setSubject(details.getSubject());

         FileSystemResource file= new FileSystemResource(new File(details.getAttachment()));

         mimeMessageHelper.addAttachment(file.getFilename(), file);

         ResponseStructure<String> responseStructure= new ResponseStructure<String>();
         
         responseStructure.setStatus(HttpStatus.OK.value());
         responseStructure.setMessage("Mail with attachment Sent Successfully...");
         responseStructure.setData("Mail Sent");
         
         
         javaMailSender.send(mimeMessage);
         return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
     

    
	} 
	
	
}
