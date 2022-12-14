package com.ty.ims.inventory_prject_boot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ims.inventory_prject_boot.dto.InwardReport;
import com.ty.ims.inventory_prject_boot.service.InwardReportService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@RestController
@RequestMapping("inwardreport")
public class InwardReportController {

	@Autowired
	InwardReportService service;

	@GetMapping
	public ResponseEntity<ResponseStructure<InwardReport>> findinwardreportbydate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		return service.servicefindinbydate(date);
	}
}
