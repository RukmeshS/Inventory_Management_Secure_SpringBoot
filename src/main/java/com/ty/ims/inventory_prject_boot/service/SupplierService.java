package com.ty.ims.inventory_prject_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.InventoryDao;
import com.ty.ims.inventory_prject_boot.dao.InwardReportDao;
import com.ty.ims.inventory_prject_boot.dao.ItemDao;
import com.ty.ims.inventory_prject_boot.dao.SupplierDao;
import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.dto.InwardReport;
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
	@Autowired
	InwardReportDao inwardReportDao;

	@Autowired
	InwardReport inwardReport;

	@Autowired
	InventoryDao inventoryDao;

	@Autowired
	Inventory inventory;

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

	public ResponseEntity<ResponseStructure<Supplier>> updateinward(Supplier supplier, int id, int itemid,
			int inventoryid) {
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<Supplier>();
		Optional<Supplier> supplier2 = dao.getInwardById(id);
		Optional<Inventory> inventoryOptional = inventoryDao.findInventorybyid(inventoryid);
		Optional<Item> existingItem = itemDao.findItembyid(itemid);
		if (supplier2.isPresent()) {
			if (inventoryOptional.isPresent()) {
				if (existingItem.isPresent()) {
					inventory = inventoryOptional.get();
					int currentItemQuantity = existingItem.get().getItem_quantity();
					List<Item> items = new ArrayList<Item>();
					List<Item> toBeUpdatedItems = supplier.getItems();
					for (Item item : toBeUpdatedItems) {
						item.setItem_id(itemid);
						item.setItem_quantity(currentItemQuantity + item.getItem_quantity());
						inventory.setProduct_id(inventoryid);
						item.setInventory(inventory);
						inwardReport.setSupplierName(supplier.getSupplierName());
						inwardReport.setSupplierEmailId(supplier.getSupplierEmailId());
						inwardReport.setSupplierPhoneNo(supplier.getSupplierPhoneNo());
						inwardReport.setInwardDate(supplier.getInwardDate());
						inwardReport.setItemName(item.getItem_name());
						inwardReport.setInwardQuantity(supplier.getInwardQuantity());
						itemDao.updateItem(item);
						inwardReportDao.saveInwardReport(inwardReport);
					}
					items.addAll(toBeUpdatedItems);
					supplier.setSupplierId(id);
					supplier.setItems(items);
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("supplier updated");
					responseStructure.setData(dao.updateInward(supplier));
				}
			}

		} else {
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.CREATED);
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
