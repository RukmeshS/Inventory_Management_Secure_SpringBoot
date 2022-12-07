package com.ty.ims.inventory_prject_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ims.inventory_prject_boot.dto.Customer;
import com.ty.ims.inventory_prject_boot.repository.CustomerRepository;

@Repository
public class CustomerDao {
	@Autowired
	CustomerRepository repository;

	public Customer saveOutward(Customer customer) {
		return repository.save(customer);
	}

	public Customer updateOutward(Customer customer) {
		return repository.save(customer);
	}

	public Customer updateOutwardById(int id) {
		Optional<Customer> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public String deleteOutwardById(int id) {
		repository.deleteById(id);
		return "outward deleted for id " + id;
	}
}
