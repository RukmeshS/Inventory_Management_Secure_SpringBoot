package com.ty.ims.inventory_prject_boot.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierId;
	
	private String supplierName;
	
	private long supplierPhoneNo;
	
	private String supplierEmailId;
	
	private Date inwardDate;
	
	private int inwardQuantity;
//	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(joinColumns = @JoinColumn, inverseJoinColumns = @JoinColumn)
	private List<Item> items;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public long getSupplierPhoneNo() {
		return supplierPhoneNo;
	}

	public void setSupplierPhoneNo(long supplierPhoneNo) {
		this.supplierPhoneNo = supplierPhoneNo;
	}

	public String getSupplierEmailId() {
		return supplierEmailId;
	}

	public void setSupplierEmailId(String supplierEmailId) {
		this.supplierEmailId = supplierEmailId;
	}

	public Date getInwardDate() {
		return inwardDate;
	}

	public void setInwardDate(Date inwardDate) {
		this.inwardDate = inwardDate;
	}

	public int getInwardQuantity() {
		return inwardQuantity;
	}

	public void setInwardQuantity(int inwardQuantity) {
		this.inwardQuantity = inwardQuantity;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
