package com.ty.ims.inventory_prject_boot.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotNull
	private Date outwardDate;
	@NotNull
	private String product;
	@NotNull
	private int outwardQuantity;
	@NotNull
	private String requestedBy;
	@ManyToMany
	@JoinTable(joinColumns =@JoinColumn,inverseJoinColumns = @JoinColumn)
	private List<Item> item;
}
