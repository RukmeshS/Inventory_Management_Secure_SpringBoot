package com.ty.ims.inventory_prject_boot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.dto.InwardReport;
import com.ty.ims.inventory_prject_boot.dto.OutwardReport;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfiguration {

	@Bean
	public Docket getDocket() {
		Contact contact = new Contact("Testyantra", "testyantra.com", "testyantra@gmail.com");
		List<VendorExtension> extenList = new ArrayList<VendorExtension>();

		ApiInfo apiInfo = new ApiInfo("Inventory_Management", "Spring boot app for Inventory Management System", "1.0",
				"testyantra.com", contact, "Apache Tomcat", "apache.com", extenList);

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ty.ims.inventory_prject_boot")).build().apiInfo(apiInfo);
	}

	@Bean
	public JavaMailSender getjavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("prashant.bag14@gmail.com");
		mailSender.setPassword("vspsmdcjkullgcej");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}

	@Bean
	public InwardReport getInwardReport() {
		return new InwardReport();
	}

	@Bean
	public Inventory getInventory() {
		return new Inventory();
	}

	@Bean
	public OutwardReport getOutwardReport() {
		return new OutwardReport();
	}

	
	// Spring security configuration
	
	
//	
//	@Bean
//	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
//				http
//					.authorizeRequests()
//					.antMatchers("/outwardreport").hasRole("ADMIN")
//					.antMatchers("/inventory").hasAnyRole("USER","ADMIN")
//					.antMatchers("/").permitAll()
//					.and().formLogin();
//				
//				
//				return http.build();
//	}
	
	
}
