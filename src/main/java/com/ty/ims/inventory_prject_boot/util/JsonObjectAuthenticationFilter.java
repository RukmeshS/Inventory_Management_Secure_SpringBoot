package com.ty.ims.inventory_prject_boot.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.ims.inventory_prject_boot.dto.LoginCredentials;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private final ObjectMapper objectMapper= new ObjectMapper();
	
	@Override
	public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedReader reader= request.getReader();
			StringBuilder sb= new StringBuilder();
			String line;
			while((line= reader.readLine()) != null) {
				sb.append(line);
			}
			LoginCredentials authRequest= objectMapper.readValue(sb.toString(), LoginCredentials.class);
			UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(
				authRequest.getEmail(),authRequest.getPassword()
					);
			
			setDetails(request, token);
			return this.getAuthenticationManager().authenticate(token);
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
