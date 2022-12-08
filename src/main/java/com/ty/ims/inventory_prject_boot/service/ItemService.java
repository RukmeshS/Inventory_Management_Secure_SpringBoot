package com.ty.ims.inventory_prject_boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.ItemDao;
import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class ItemService {

	@Autowired
	ItemDao dao;

	public ResponseEntity<ResponseStructure<Item>> serviceSaveItem(Item item) {
		ResponseStructure<Item> responseStructure = new ResponseStructure<Item>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Inventory Item Created");
		responseStructure.setData(dao.saveItem(item));
		return new ResponseEntity<ResponseStructure<Item>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Item>> serviceUpdateItem(Item item, int id) {
		ResponseStructure<Item> responseStructure = new ResponseStructure<Item>();
		Optional<Item> optional = dao.findItembyid(id);
		if (optional.isPresent()) {
			item.setItem_id(id);
			responseStructure.setStatus(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Inventory Item updated");
			responseStructure.setData(dao.updateItem(item));
			return new ResponseEntity<ResponseStructure<Item>>(responseStructure, HttpStatus.ACCEPTED);
		}
		throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<Item>> serviceFinditembyid(int id) {
		ResponseStructure<Item> responseStructure = new ResponseStructure<Item>();
		Optional<Item> optional = dao.findItembyid(id);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Inventory Item Found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Item>>(responseStructure, HttpStatus.FOUND);
		}
		throw new NoSuchIdFoundException();
	}

	public ResponseEntity<ResponseStructure<Item>> serviceDeleteItem(int id) {
		ResponseStructure<Item> responseStructure = new ResponseStructure<Item>();
		Optional<Item> optional = dao.findItembyid(id);
		if (optional.isPresent()) {
			dao.deleteItem(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Inventory Item Deleted");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Item>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchIdFoundException();
	}
}
