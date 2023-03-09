package com.ty.ims.inventory_prject_boot.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OutwardReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	private String customerName;
	
	private String customerEmailId;
	
	private long customerPhoneNo;
	
	private Date outwardDate;
	
	private int outwardQuantity;

	private int ItemName;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public long getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(long customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	public Date getOutwardDate() {
		return outwardDate;
	}

	public void setOutwardDate(Date outwardDate) {
		this.outwardDate = outwardDate;
	}

	public int getOutwardQuantity() {
		return outwardQuantity;
	}

	public void setOutwardQuantity(int outwardQuantity) {
		this.outwardQuantity = outwardQuantity;
	}

	public int getItemName() {
		return ItemName;
	}

	public void setItemName(int itemName) {
		ItemName = itemName;
	}
	
	
	
}
