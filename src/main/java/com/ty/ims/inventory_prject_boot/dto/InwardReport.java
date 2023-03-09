package com.ty.ims.inventory_prject_boot.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class InwardReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String supplierName;
	
	private long supplierPhoneNo;
	
	private String supplierEmailId;

	private Date inwardDate;
	
	private int inwardQuantity;
	
	private String ItemName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}



		
	
	

	
	
	
}
