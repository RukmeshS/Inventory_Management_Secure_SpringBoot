package com.ty.ims.inventory_prject_boot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
		Contact contact = new Contact("Testyantra","testyantra.com","testyantra@gmail.com");
		List<VendorExtension>  extenList = new ArrayList<VendorExtension>();
		
		ApiInfo apiInfo = new ApiInfo("Inventory_Management", "Spring boot app for Inventory Management System", "1.0", "testyantra.com", contact, "Apache Tomcat", "apache.com", extenList);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ty.ims.inventory_prject_boot")).build();
	}
	
}
