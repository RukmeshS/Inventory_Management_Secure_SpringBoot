package com.ty.ims.inventory_prject_boot.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
public class SecurityBeans {

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@SneakyThrows
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
