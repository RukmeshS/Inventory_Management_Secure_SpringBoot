package com.ty.ims.inventory_prject_boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.AdminDao;
import com.ty.ims.inventory_prject_boot.dto.Admin;
import com.ty.ims.inventory_prject_boot.exception.AdminRegisterNotAllowedException;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin>  responseStructure = new ResponseStructure<Admin>();
		
		if(adminDao.getAllAdmin().size()<=3) {
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Admin created");
			responseStructure.setData(adminDao.saveAdmin(admin));
		}
		else {
			 throw new AdminRegisterNotAllowedException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.CREATED);
		
		return responseEntity;
	
		
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id){
		ResponseStructure<Admin>  responseStructure = new ResponseStructure<Admin>();

		Optional<Admin> admin2= adminDao.getAdminById(id);
		
		if(admin2.isPresent()) {
			admin.setId(id);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Admin Found & Updated");
			responseStructure.setData(adminDao.saveAdmin(admin));
		}
		else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.CREATED);

		return responseEntity;
	}
	
	
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id){
			
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		
		Optional<Admin> optional= adminDao.getAdminById(id);
		
		if(optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Admin Found");
			responseStructure.setData(optional.get());
			
		}else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.FOUND);

		return responseEntity;
	}
	
	
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id){
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Optional<Admin> optional = adminDao.getAdminById(id);
		
		if(optional.isPresent()) {
			adminDao.deleteAdmin(optional.get());
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Admin Found and Deleted");
			responseStructure.setData(optional.get());
		}
		else {
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.FOUND);

		
		return responseEntity;
		
		
	}
	
	
}
