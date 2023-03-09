package com.ty.ims.inventory_prject_boot.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dto.Customer;
import com.ty.ims.inventory_prject_boot.exception.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserService implements UserDetailsService{

	
	private final  CustomerSevice customerSevice;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws CustomerNotFoundException {
		Optional<Customer> customer=customerSevice.getUserByEmail(email);
		
		return new User(customer.get().getCustomerEmail(), customer.get().getCustomerPassword(), customer.get().isEnabled(),true,true,true,customer.get().getAuthorities());
		
	}
	
	
	
}
