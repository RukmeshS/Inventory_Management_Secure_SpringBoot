package com.ty.ims.inventory_prject_boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.Customer;

public interface JwtUserRepository extends JpaRepository<Customer, String>{

	Optional<Customer> findByCustomerName(String customerName);
	Optional<Customer> findByCustomerEmail(String customerEmail);
}
