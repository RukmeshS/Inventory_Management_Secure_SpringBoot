package com.ty.ims.inventory_prject_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ims.inventory_prject_boot.dto.Inventory;
import com.ty.ims.inventory_prject_boot.repository.InventoryRepository;

@Repository
public class InventoryDao {

	@Autowired
	InventoryRepository repository;

	public Inventory saveInventory(Inventory inventory) {
		return repository.save(inventory);
	}

	public Inventory updateInventory(Inventory inventory) {
		return repository.save(inventory);
	}

	public Optional<Inventory> findInventorybyid(int id) {
		return repository.findById(id);
	}

	public void deleteInventory(Inventory inventory) {
		repository.delete(inventory);
	}

}
