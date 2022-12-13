package com.ty.ims.inventory_prject_boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ims.inventory_prject_boot.dto.OutwardReport;
import com.ty.ims.inventory_prject_boot.repository.OutwardReportRepository;

@Repository
public class OutwardReportDao {

	@Autowired
	OutwardReportRepository outwardReportRepository;

	public OutwardReport saveOutwardReport(OutwardReport outwardReport) {

		return outwardReportRepository.save(outwardReport);

	}
}
