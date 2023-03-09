package com.ty.ims.inventory_prject_boot.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	@NotNull
	private String product_name;
	private int product_quantity;

	@OneToMany(fetch=FetchType.EAGER,mappedBy = "inventory" , cascade = CascadeType.PERSIST) // (mappedBy = "inventory")
	private List<Item> item;

}
