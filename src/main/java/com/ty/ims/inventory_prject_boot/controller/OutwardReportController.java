package com.ty.ims.inventory_prject_boot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ims.inventory_prject_boot.dto.OutwardReport;
import com.ty.ims.inventory_prject_boot.service.OutwardReportService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@RestController
@RequestMapping("outwardreport")
public class OutwardReportController {

	@Autowired
	OutwardReportService service;

	@GetMapping
//	public OutwardReport findOutwardreportbydate(
//			@RequestParam("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startdate,
//			@RequestParam("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date enddate) {
//		return service.servicefindoutbydate(startdate, enddate);
//	}

	public ResponseEntity<ResponseStructure<OutwardReport>> findOutwardreportbydate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		return service.servicefindoutbydate(date);
	}
}
