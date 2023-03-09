package com.ty.ims.inventory_prject_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	abstract Customer findByCustomerId(String customerId);
}
