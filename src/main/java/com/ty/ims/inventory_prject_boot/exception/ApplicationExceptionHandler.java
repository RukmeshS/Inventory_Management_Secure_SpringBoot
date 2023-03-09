package com.ty.ims.inventory_prject_boot.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noSuchIdFoundExceptionHandler(NoSuchIdFoundException exception){
		
		ResponseStructure<String>  responseStructure = new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("ID Not FOund");
		responseStructure.setData(exception.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
		
		
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> er = ex.getAllErrors();
		
		Map<String,String> map = new LinkedHashMap<String, String>();
		
		for (ObjectError objectError : er) {
			
			String message=objectError.getDefaultMessage();
			String fieldname= ((FieldError)objectError).getField();
			map.put(message, fieldname);
		}
		
		
		ResponseStructure<Map<String,String>>  responseStructure = new ResponseStructure<Map<String,String>>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("No Proper Input");
		responseStructure.setData(map);
		
		
		return new ResponseEntity<>(responseStructure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminRegisterNotAllowedExceptionHandler(AdminRegisterNotAllowedException exception){
		ResponseStructure<String>  responseStructure = new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		responseStructure.setMessage("No more admins are allowed");
		responseStructure.setData(exception.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_ACCEPTABLE);
		
		return responseEntity;
		
		
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> emailNotSendExceptionHandler(EmailNotSendException exception){
		
		ResponseStructure<String>  responseStructure = new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.UNAUTHORIZED.value());
		responseStructure.setMessage("Error while Sending The Mail");
		responseStructure.setData(exception.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.UNAUTHORIZED);
		
		return responseEntity;
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> wrongEmailIDPasswordExceptionHandler(WrongEmailIDPasswordException exception){
		
		ResponseStructure<String>  responseStructure = new ResponseStructure<String>();
		responseStructure.setStatus(HttpStatus.UNAUTHORIZED.value());
		responseStructure.setMessage("Wrong Email ID & Password");
		responseStructure.setData(exception.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.UNAUTHORIZED);
		
		return responseEntity;
	}
	
	
	
	
}
