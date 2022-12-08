package com.ty.ims.inventory_prject_boot.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int item_id;
	@NotNull
	private String item_name;
	@NotNull
	private int item_quantity;
	@NotNull
	private double item_price;

	@JsonIgnore
	@ManyToMany(mappedBy = "item")
	private List<Customer> customer;

	@JsonIgnore
	@ManyToMany(mappedBy = "items")
	private List<Supplier> suppliers;
}
