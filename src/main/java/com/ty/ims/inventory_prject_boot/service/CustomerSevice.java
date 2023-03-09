package com.ty.ims.inventory_prject_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.CustomerDao;
import com.ty.ims.inventory_prject_boot.dao.InventoryDao;
import com.ty.ims.inventory_prject_boot.dao.ItemDao;
import com.ty.ims.inventory_prject_boot.dao.OutwardReportDao;
import com.ty.ims.inventory_prject_boot.dto.Customer;
import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.dto.OutwardReport;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.repository.JwtUserRepository;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;
import com.ty.ims.inventory_prject_boot.util.SecurityBeans;

@Service
public class CustomerSevice {
	@Autowired
	private CustomerDao dao;
	@Autowired
	ItemDao itemDao;

	@Autowired
	OutwardReport outwardReport;

	@Autowired
	OutwardReportDao outwardReportDao;

	@Autowired
	InventoryDao inventoryDao;

	@Autowired
	Inventory inventory;
	
	@Autowired
	private JwtUserRepository jwtUserRepository;
	
	@Autowired
	private SecurityBeans securityBeans;
	
	private static final Logger logger= Logger.getLogger(CustomerSevice.class);

	
	public Optional<Customer> getUserByEmail(String email){
		return jwtUserRepository.findByCustomerEmail(email);
	}
 
	
	
	
	public ResponseEntity<ResponseStructure<Customer>> saveOutward(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		List<Item> listItems = customer.getItem();
		logger.debug("Customer outward Items ");
		customer.setItem(listItems);
		//customer.setCustomerPassword(securityBeans.getBCryptPasswordEncoder().encode(customer.getCustomerPassword()));
		logger.info("Getting all List fo items");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("customer saved");
		responseStructure.setData(dao.saveOutward(customer));
		logger.info("Customer added Successfully");
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> saveitems(String customerid, int itemid) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		logger.info("Searcing Items");
		Optional<Item> item = itemDao.findItembyid(itemid);
		List<Item> items1 = (List) item.get();
		logger.info("getting list of Items");
		Optional<Customer> customer = dao.getOutwardById(customerid);
		logger.info("Adding Item to the customer");
		customer.get().setCustomerId(customerid);
		customer.get().setItem(items1);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("item saved for customer " + customerid);
		responseStructure.setData(dao.saveOutward(customer.get()));
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> upadetOutward(Customer customer, String id, int itemId,
			int inventoryid) {

		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Optional<Customer> customer2 = dao.getOutwardById(id);
		Optional<Item> existingItem = itemDao.findItembyid(itemId);
		Optional<Inventory> inventoryOptional = inventoryDao.findInventorybyid(inventoryid);
		logger.debug("Updating Customer");
		
		if (customer2.isPresent()) {
			logger.info("Customer Found in the  Database");
			if (inventoryOptional.isPresent()) {
				
				if (existingItem.isPresent()) {
					logger.info("Requested Item found in the Inventory");
					inventory = inventoryOptional.get();
					int currentItemQuantity = existingItem.get().getItem_quantity();
					List<Item> items = new ArrayList<Item>();
					List<Item> toBeUpdatedItems = customer.getItem();
					logger.info("Getting list of Items");
					for (Item item : toBeUpdatedItems) {
						item.setItem_id(itemId);
						item.setItem_quantity(currentItemQuantity - item.getItem_quantity());
						inventory.setProduct_id(inventoryid);
						item.setInventory(inventory);
						outwardReport.setCustomerName(customer.getCustomerName());
						outwardReport.setCustomerEmailId(customer.getCustomerEmail());
						outwardReport.setCustomerPhoneNo(customer.getCustomerPhoneNo());
						outwardReport.setOutwardDate(customer.getOutwardDate());
						outwardReport.setOutwardQuantity(customer.getOutwardQuantity());
						outwardReport.setItemName(item.getItem_id());
						itemDao.updateItem(item);
						outwardReportDao.saveOutwardReport(outwardReport);
					}
					logger.info("Updated the Customer with requested items");
					items.addAll(toBeUpdatedItems);
					customer.setCustomerId(id);
					customer.setItem(items);
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("customer updated");
					responseStructure.setData(dao.updateOutward(customer));
				}
			}
		} else {
			logger.fatal("No Such Customer found in the database");
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> getOutwardById(String id) {
		Optional<Customer> customer = dao.getOutwardById(id);
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		logger.info("Searching for the Customer");
		if (customer.isPresent()) {
			logger.info("Customer found in the database");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("customer fetched for the id" + id);
			responseStructure.setData(dao.getOutwardById(id).get());
		} else {
			logger.fatal("No Customer found in the database");
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.FOUND);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> deleteOutwardById(String id) {
		Optional<Customer> customer = dao.getOutwardById(id);
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		logger.info("Searching for the Customer");
		if (customer.isPresent()) {
			logger.info("Customer found in the database");
			dao.deleteOutward(customer.get());
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("customer deleted for the id" + id);
			responseStructure.setData(customer.get());
		} else {
			logger.fatal("No Customer found in the database");

			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.FOUND);
		return responseEntity;
	}

}
