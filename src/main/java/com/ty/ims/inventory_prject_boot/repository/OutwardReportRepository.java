package com.ty.ims.inventory_prject_boot.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.OutwardReport;

public interface OutwardReportRepository extends JpaRepository<OutwardReport, Integer> {

	
	//List<OutwardReport> findByoutwardDateBetween (Date startdate , Date enddate); 
	public Optional<OutwardReport> findByoutwardDateAfter (Date date);

}
