package com.ty.ims.inventory_prject_boot.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.InwardReportDao;
import com.ty.ims.inventory_prject_boot.dto.InwardReport;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;


@Service
public class InwardReportService {

	@Autowired
	InwardReportDao dao;
	
	public ResponseEntity<ResponseStructure<InwardReport>> servicefindinbydate(Date date) {
		ResponseStructure<InwardReport> responseStructure = new ResponseStructure<InwardReport>();
		Optional<InwardReport> optional = dao.findByStartDateBeInwardReport(date);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage(" Report Found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<InwardReport>>(responseStructure, HttpStatus.FOUND);
		}
		throw new NoSuchIdFoundException();
	}
}
