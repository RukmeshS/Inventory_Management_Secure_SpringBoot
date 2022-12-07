package com.ty.ims.inventory_prject_boot.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String adminEmail;
	@NotNull
	private String adminPassword;
	@NotNull
	private String adminName;
	@Column(unique = true)
	private String adminRole;
	@NotNull
	private long adminPhone; 
}
