package com.ty.ims.inventory_prject_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

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

	public ResponseEntity<ResponseStructure<Customer>> saveOutward(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		List<Item> listItems = customer.getItem();
		customer.setItem(listItems);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("customer saved");
		responseStructure.setData(dao.saveOutward(customer));
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> saveitems(int customerid, int itemid) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Optional<Item> item = itemDao.findItembyid(itemid);
		List<Item> items1 = (List) item.get();
		Optional<Customer> customer = dao.getOutwardById(customerid);
		customer.get().setCustomerId(customerid);
		customer.get().setItem(items1);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("item saved for customer " + customerid);
		responseStructure.setData(dao.saveOutward(customer.get()));
		ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Customer>> upadetOutward(Customer customer, int id, int itemId,
			int inventoryid) {

		ResponseStructure<Customer> responseStructure = new ResponseStructure<Customer>();
		Optional<Customer> customer2 = dao.getOutwardById(id);
		Optional<Item> existingItem = itemDao.findItembyid(itemId);
		Optional<Inventory> inventoryOptional = inventoryDao.findInventorybyid(inventoryid);
		if (customer2.isPresent()) {
			if (inventoryOptional.isPresent()) {
				if (existingItem.isPresent()) {
					inventory = inventoryOptional.get();
					int currentItemQuantity = existingItem.get().getItem_quantity();
					List<Item> items = new ArrayList<Item>();
					List<Item> toBeUpdatedItems = customer.getItem();
					for (Item item : toBeUpdatedItems) {
						item.setItem_id(itemId);
						item.setItem_quantity(currentItemQuantity - item.getItem_quantity());
						inventory.setProduct_id(inventoryid);
						item.setInventory(inventory);
						outwardReport.setCustomerName(customer.getCustomerName());
						outwardReport.setCustomerEmailId(customer.getCustomerEmailId());
						outwardReport.setCustomerPhoneNo(customer.getCustomerPhoneNo());
						outwardReport.setOutwardDate(customer.getOutwardDate());
						outwardReport.setOutwardQuantity(customer.getOutwardQuantity());
						outwardReport.setItemName(item.getItem_id());
						itemDao.updateItem(item);
						outwardReportDao.saveOutwardReport(outwardReport);
					}
					items.addAll(toBeUpdatedItems);
					customer.setCustomerId(id);
					customer.setItem(items);
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("customer updated");
					responseStructure.setData(dao.updateOutward(customer));
				}
			}
		} else {
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.CREATED);
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
