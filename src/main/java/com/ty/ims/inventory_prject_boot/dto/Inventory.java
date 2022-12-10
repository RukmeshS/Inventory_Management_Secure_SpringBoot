package com.ty.ims.inventory_prject_boot.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	@NotNull
	private String product_name;
	private int product_quantity;

	@OneToMany(mappedBy = "inventory" , cascade = CascadeType.PERSIST) // (mappedBy = "inventory")
	private List<Item> item;

}
