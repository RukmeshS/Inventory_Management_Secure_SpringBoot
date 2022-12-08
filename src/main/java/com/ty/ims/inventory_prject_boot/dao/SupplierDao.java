package com.ty.ims.inventory_prject_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.repository.SupplierRepository;

@Repository
public class SupplierDao {
	@Autowired
	private SupplierRepository repository;

	public Supplier saveInward(Supplier supplier) {
		return repository.save(supplier);
	}

	public Supplier updateInward(Supplier supplier) {
		return repository.save(supplier);
	}

	public Optional<Supplier> getInwardById(int id) {
		return repository.findById(id);
	}

	public void deleteInward(Supplier supplier) {
		repository.delete(supplier);
	}
}
