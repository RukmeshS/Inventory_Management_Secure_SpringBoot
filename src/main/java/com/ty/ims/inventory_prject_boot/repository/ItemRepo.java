package com.ty.ims.inventory_prject_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
