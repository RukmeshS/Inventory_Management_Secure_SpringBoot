package com.ty.ims.inventory_prject_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
