package com.ty.ims.inventory_prject_boot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ims.inventory_prject_boot.dto.Supplier;
import com.ty.ims.inventory_prject_boot.repository.SupplierRepository;

@Repository
public class SupplierDao {
	@Autowired
	SupplierRepository repository;

	public Supplier saveInward(Supplier supplier) {
		return repository.save(supplier);
	}

	public Supplier updateInward(Supplier supplier) {
		return repository.save(supplier);
	}

	public Supplier getInwardById(int id) {
		Optional<Supplier> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public String deleteInwardById(int id) {
		repository.deleteById(id);
		return "inward deleted for id " + id;
	}
}
