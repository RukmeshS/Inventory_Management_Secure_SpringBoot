package com.ty.ims.inventory_prject_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ims.inventory_prject_boot.dto.Item;
import com.ty.ims.inventory_prject_boot.repository.ItemRepository;

@Repository
public class ItemDao {

	@Autowired
	ItemRepository repository;

	public Item saveItem(Item item) {
		return repository.save(item);
	}

	public Item updateItem(Item item) {
		return repository.save(item);
	}

	public Item findItembyid(int id) {
		Optional<Item> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public String deleteItem(int id) {
		repository.deleteById(id);
		return "Item Deleted";
	}
}
