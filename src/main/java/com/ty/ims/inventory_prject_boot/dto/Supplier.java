package com.ty.ims.inventory_prject_boot.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierId;
	@NotNull
	private Date inwardDate;
	@NotNull
	private int inwardQuantity;
	@NotNull
	private String requestedBy;
	@NotNull
	private String Product;
}
