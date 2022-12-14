package com.ty.ims.inventory_prject_boot.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.OutwardReportDao;
import com.ty.ims.inventory_prject_boot.dto.OutwardReport;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class OutwardReportService {
	
	@Autowired
	OutwardReportDao dao;

//	public OutwardReport servicefindoutbydate(Date date, Date date1) {
//		return dao.findByStartDateBeOutwardReport(date, date1);
//	}

//	public OutwardReport servicefindoutbydateaaa(Date date) {
//		Optional<OutwardReport> optional = dao.findByStartDateBeOutwardReport(date);
//		if (optional.isPresent()) {
//			date.setDate(optional.get());
//		//	return dao.findByStartDateBeOutwardReport(date);
//		}
//throw new NoSuchIdFoundException();	
//	}

	public ResponseEntity<ResponseStructure<OutwardReport>> servicefindoutbydate(Date date) {
		ResponseStructure<OutwardReport> responseStructure = new ResponseStructure<OutwardReport>();
		Optional<OutwardReport> optional = dao.findByStartDateBeOutwardReport(date);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage(" Report Found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<OutwardReport>>(responseStructure, HttpStatus.FOUND);
		}
		throw new NoSuchIdFoundException();
	}
	
}
