package com.ty.ims.inventory_prject_boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.InventoryDao;
import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class InventoryService {

	@Autowired
	InventoryDao dao;

	public ResponseEntity<ResponseStructure<Inventory>> serviceSaveInventory(Inventory inventory) {
		ResponseStructure<Inventory> responseStructure = new ResponseStructure<Inventory>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Inventory Product Created");
		responseStructure.setData(dao.saveInventory(inventory));
		return new ResponseEntity<ResponseStructure<Inventory>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Inventory>> serviceUpdateInventory(Inventory inventory, int id) {
		Optional<Inventory> optional = dao.findInventorybyid(id);
		if (optional.isPresent()) {
			inventory.setProduct_id(id);
			ResponseStructure<Inventory> responseStructure = new ResponseStructure<Inventory>();
			responseStructure.setStatus(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Inventory Product Update");
			responseStructure.setData(dao.updateInventory(inventory));
			return new ResponseEntity<ResponseStructure<Inventory>>(responseStructure, HttpStatus.ACCEPTED);
		}
		throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<Inventory>> serviceFindInvById(int id) {
		Optional<Inventory> optional = dao.findInventorybyid(id);
		ResponseStructure<Inventory> responseStructure = new ResponseStructure<Inventory>();
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Inventory Product Found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Inventory>>(responseStructure, HttpStatus.FOUND);
		}
		throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<Inventory>> serviceDeleteInventory(int id) {
		ResponseStructure<Inventory> responseStructure = new ResponseStructure<Inventory>();
		Optional<Inventory> optional = dao.findInventorybyid(id);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Inventory Product Deleted");
			responseStructure.setData(optional.get());
			dao.deleteInventory(optional.get());
			return new ResponseEntity<ResponseStructure<Inventory>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchIdFoundException();
	}
}
