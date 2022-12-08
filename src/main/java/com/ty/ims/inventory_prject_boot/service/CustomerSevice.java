package com.ty.ims.inventory_prject_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.ims.inventory_prject_boot.dao.CustomerDao;
import com.ty.ims.inventory_prject_boot.dto.Customer;
import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class CustomerSevice {
	@Autowired
	private CustomerDao dao;

	public ResponseEntity<ResponseStructure<Customer>> saveOutward(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("customer saved");
		responseStructure.setData(dao.saveOutward(customer));
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> upadetOutward(Customer customer, int id) {

		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Optional<Customer> customer2 = dao.getOutwardById(id);
		if (customer2.isPresent()) {
//			List<Item> items = customer.getItem();
			customer.setCustomerId(id);
//			customer.setItem(items);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("customer updated");
			responseStructure.setData(dao.saveOutward(customer));
		} else {
			throw new NoSuchIdFoundException();
		}

		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> getOutwardById(int id) {
		Optional<Customer> customer = dao.getOutwardById(id);
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		if (customer.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("customer fetched for the id" + id);
			responseStructure.setData(dao.getOutwardById(id).get());
		} else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.FOUND);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> deleteOutwardById(int id) {
		Optional<Customer> customer = dao.getOutwardById(id);
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		if (customer.isPresent()) {
			dao.deleteOutward(customer.get());
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("customer deleted for the id" + id);
			responseStructure.setData(customer.get());
		} else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.FOUND);
		return responseEntity;
	}

}
