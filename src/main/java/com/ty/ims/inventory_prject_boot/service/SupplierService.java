package com.ty.ims.inventory_prject_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.ItemDao;
import com.ty.ims.inventory_prject_boot.dao.SupplierDao;
import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class SupplierService {
	@Autowired
	private SupplierDao dao;
	
	@Autowired
	ItemDao itemDao;

	public ResponseEntity<ResponseStructure<Supplier>> saveinward(Supplier supplier) {
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<Supplier>();
		List<Item> listItems = supplier.getItems();
		supplier.setItems(listItems);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("supplier saved");
		responseStructure.setData(dao.saveInward(supplier));
		ResponseEntity<ResponseStructure<Supplier>> responseEntity = new ResponseEntity<ResponseStructure<Supplier>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Supplier>> updateinward(Supplier supplier, int id,int itemid) {
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<Supplier>();
		Optional<Supplier> supplier2 = dao.getInwardById(id);
		
		Optional<Item> existingItem= itemDao.findItembyid(itemid);
		
		
		if (supplier2.isPresent()) {
			if(existingItem.isPresent()) {
				List<Item> items = new ArrayList<Item>();
				List<Item> toBeUpdatedItems=supplier.getItems();
				for (Item item : toBeUpdatedItems) {
					item.setItem_id(itemid);
					
					itemDao.updateItem(item);
					
				}
				items.addAll(toBeUpdatedItems);
				
				supplier.setSupplierId(id);
				supplier.setItems(items);
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("supplier updated");
				responseStructure.setData(dao.updateInward(supplier));
			
				
			}	
		} else {
			throw new NoSuchIdFoundException();
		}
		
		return new ResponseEntity<ResponseStructure<Supplier>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Supplier>> getInwardById(int id) {
		Optional<Supplier> supplier = dao.getInwardById(id);
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<Supplier>();
		if (supplier.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("supplier fetched for the id" + id);
			responseStructure.setData(dao.getInwardById(id).get());
		} else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Supplier>> responseEntity = new ResponseEntity<ResponseStructure<Supplier>>(
				responseStructure, HttpStatus.FOUND);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Supplier>> deleteInwardById(int id) {
		Optional<Supplier> supplier = dao.getInwardById(id);
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<Supplier>();
		if (supplier.isPresent()) {
			dao.deleteInward(supplier.get());
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("supplier deleted for the id" + id);
			responseStructure.setData(supplier.get());
		} else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Supplier>> responseEntity = new ResponseEntity<ResponseStructure<Supplier>>(
				responseStructure, HttpStatus.FOUND);
		return responseEntity;
	}

}
