package com.ty.ims.inventory_prject_boot.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ims.inventory_prject_boot.dto.InwardReport;

public interface InwardReportRepository extends JpaRepository<InwardReport, Integer> {

	public Optional<InwardReport> findByinwardDateAfter (Date date);
}
