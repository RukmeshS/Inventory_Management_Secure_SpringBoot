package com.ty.ims.inventory_prject_boot.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	private String item_name;
	private int item_quantity;
	private double item_price;

	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Inventory inventory;

	@JsonIgnore
	@ManyToMany(mappedBy = "item")
	private List<Customer> customer;

	@JsonIgnore
	@ManyToMany(mappedBy = "items")
	private List<Supplier> suppliers;
}
