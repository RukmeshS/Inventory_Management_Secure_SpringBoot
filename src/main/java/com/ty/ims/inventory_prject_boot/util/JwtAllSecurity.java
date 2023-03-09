package com.ty.ims.inventory_prject_boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.ty.ims.inventory_prject_boot.service.JwtUserService;

@Configuration
@EnableWebSecurity
public class JwtAllSecurity {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private final AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private final JwtUserService jwtUserService;
	
	private final String secret;
	
	public JwtAllSecurity() {
		this.authSuccessHandler = new AuthSuccessHandler();
		this.jwtUserService = null;
		this.secret = "";
		
	}
	
	
	
	
	public JwtAllSecurity(AuthSuccessHandler authSuccessHandler, JwtUserService jwtUserService, String secret) {
		super();
		this.authSuccessHandler = authSuccessHandler;
		this.jwtUserService = jwtUserService;
		this.secret = secret;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.cors()
			.and()
			.csrf()
			.disable()
			.authorizeHttpRequests((auth)->{
				try {
					auth.antMatchers("/userss").hasRole("USER")
					.anyRequest().permitAll()
					.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.addFilter(authenticationFilter())
					.addFilter(new JwtAuthorizationFilter(authenticationManager, jwtUserService, secret))
					.exceptionHandling()
					.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
				}catch(Exception e) {
					throw new RuntimeException();
				}
			})
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	 
	@Bean
	public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
		JsonObjectAuthenticationFilter filter= new JsonObjectAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(authSuccessHandler);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}
	
	


	
//	@Bean
//	public InMemoryUserDetailsManager getInMemoryUserDetails() {
//		
//		UserDetails admin= User.builder()
//							.username("admin")
//							.password(getBCryptPasswordEncoder().encode("password"))
//							.roles("ADMIN")
//							.build();
//		
//		UserDetails user= User.builder()
//				.username("user")
//				.password(getBCryptPasswordEncoder().encode("password"))
//				.roles("USER")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(admin,user);
//		
//	}
	
	
	
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		
//		
//		http.csrf().disable().cors().disable().authorizeRequests()
//		.antMatchers("/outwardreport").hasAnyRole("Admin")
//		.antMatchers("/").permitAll().anyRequest().authenticated()
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
}
