package com.ty.ims.inventory_prject_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.ims.inventory_prject_boot.dao.SupplierDao;
import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class SupplierService {
	@Autowired
	private SupplierDao dao;

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

	public ResponseEntity<ResponseStructure<Supplier>> updateinward(Supplier supplier, int id) {
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<Supplier>();
		Optional<Supplier> supplier2 = dao.getInwardById(id);
		if (supplier2.isPresent()) {
			List<Item> items = supplier.getItems();
			List<Item> items1 = supplier2.get().getItems();
			
			//not working
			for (Item item : items) {
				for (Item item1 : items1) {
					item.setItem_id(item1.getItem_id());
				}

			}

			supplier.setItems(items1);
			supplier.setSupplierId(id);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("supplier updated");
			responseStructure.setData(dao.updateInward(supplier));
		} else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Supplier>> responseEntity = new ResponseEntity<ResponseStructure<Supplier>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
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
