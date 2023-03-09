package com.ty.ims.inventory_prject_boot.util;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.ims.inventory_prject_boot.service.CustomerSevice;
import com.ty.ims.inventory_prject_boot.service.JwtUserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	
	private final String secret;
	private final int expTime;
	private final ObjectMapper  objectMapper= new ObjectMapper();
	
	@Autowired
	private final CustomerSevice customerSevice;

	
	

	public AuthSuccessHandler(String secret, int expTime, CustomerSevice customerSevice) {
		super();
		this.secret = secret;
		this.expTime = expTime;
		this.customerSevice = customerSevice;
	}



	public AuthSuccessHandler() {
		this.secret = "";
		this.expTime = 0;
		this.customerSevice = new CustomerSevice();
	
	}



	@Override
	public void onAuthenticationSuccess(HttpServletRequest request , HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
		
		UserDetails principal= (UserDetails) authentication.getPrincipal();
		String token= JWT.create().withSubject(customerSevice.getUserByEmail(principal.getUsername()).get().getCustomerEmail())
				.withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli() + expTime))
				.sign(Algorithm.HMAC256(secret));
		
		response.addHeader("Authorization",  token);
		response.addHeader("Content-Type", "application/json");
		response.getWriter().write("token: "+token);
	}
	
}
