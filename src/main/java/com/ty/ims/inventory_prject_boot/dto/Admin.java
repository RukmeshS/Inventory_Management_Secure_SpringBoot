package com.ty.ims.inventory_prject_boot.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String adminEmail;
	@NotNull
	private String adminPassword;
	
	private String adminName;
	@Column(unique = true)
	private String adminRole;
	
	private long adminPhone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public long getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(long adminPhone) {
		this.adminPhone = adminPhone;
	}

	public Admin(int id, @NotNull String adminEmail, @NotNull String adminPassword, @NotNull String adminName,
			String adminRole, @NotNull long adminPhone) {
		super();
		this.id = id;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.adminPhone = adminPhone;
	}

	public Admin() {
		super();
	}
	
	
	
	

}
