package com.ty.ims.inventory_prject_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
