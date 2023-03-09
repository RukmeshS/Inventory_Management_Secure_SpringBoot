package com.ty.ims.inventory_prject_boot.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.net.HttpHeaders;
import com.ty.ims.inventory_prject_boot.service.JwtUserService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private static final String TOKEN_PREFIX="Bearer :";
	
	private final JwtUserService jwtUserService;
	
	private final String secret;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUserService jwtUserService,
			String secret) {
		super(authenticationManager);
		this.jwtUserService = jwtUserService;
		this.secret = secret;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UsernamePasswordAuthenticationToken auth= getAuthentication(request);
		if(auth==null) {
			chain.doFilter(request, response);
			return;
		}
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token= request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(token == null || !token.startsWith(TOKEN_PREFIX)) {
			return null;
		}
		
		String email = JWT.require(Algorithm.HMAC256(secret))
				.build()
				.verify(token.replace(TOKEN_PREFIX, ""))
				.getSubject();
		
		if(email == null) return null;
		
		UserDetails userDetails= jwtUserService.loadUserByUsername(email);
		
		
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,userDetails.getAuthorities());
	}
	
	
	
	
	
}
