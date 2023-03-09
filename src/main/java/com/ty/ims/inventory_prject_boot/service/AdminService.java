package com.ty.ims.inventory_prject_boot.service;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ims.inventory_prject_boot.dao.AdminDao;
import com.ty.ims.inventory_prject_boot.dto.Admin;
import com.ty.ims.inventory_prject_boot.exception.AdminRegisterNotAllowedException;
import com.ty.ims.inventory_prject_boot.exception.NoSuchIdFoundException;
import com.ty.ims.inventory_prject_boot.exception.WrongEmailIDPasswordException;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	private static final String SECRET_KEY= "my_super_secret_key_ho_ho_ho";

	private static final String SALT = "ssshhhhhhhhhhh!!!!";
	
	private static final Logger logger= Logger.getLogger(AdminService.class);

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();


		if (adminDao.getAllAdmin().size() < 3) {
			admin.setAdminPassword(encrypt(admin.getAdminPassword()));
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Admin created");
			responseStructure.setData(adminDao.saveAdmin(admin));
			logger.info("User succesfully saved in database");
			
		} else {
			logger.debug("Adding more Admin");
			logger.fatal("Cannot add more admins to the database");
			throw new AdminRegisterNotAllowedException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.CREATED);

		return responseEntity;

	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();

		Optional<Admin> admin2 = adminDao.getAdminById(id);
		logger.info("Searching for the requested admin ID");
		if (admin2.isPresent()) {
			admin.setId(id);
			logger.info("Admin with ID "+id+" found in the database");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Admin Found & Updated");
			responseStructure.setData(adminDao.saveAdmin(admin));
			logger.info("Admin data is updated");
		} else {
			logger.fatal("Requested ID is not present in the database");
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.CREATED);

		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();

		Optional<Admin> optional = adminDao.getAdminById(id);
		logger.info("Searching for the requested admin ID");
		if (optional.isPresent()) {
			Admin admin=optional.get();
			logger.info("Admin with ID "+id+" found in the database");
			admin.setAdminPassword(decrypt(admin.getAdminPassword()));
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Admin Found");
			responseStructure.setData(admin);

		} else {
			logger.fatal("Requested ID is not present in the database");
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.FOUND);

		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Optional<Admin> optional = adminDao.getAdminById(id);
		logger.info("Searching for the requested admin ");
		if (optional.isPresent()) {
			adminDao.deleteAdmin(optional.get());
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Admin Found and Deleted");
			responseStructure.setData(optional.get());
		} else {
			logger.fatal("Requested ID is not present in the database");
			throw new NoSuchIdFoundException();
		}
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.FOUND);

		return responseEntity;

	}

	
	
	public ResponseEntity<ResponseStructure<Admin>> loginAdmin(Admin admin){
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();

		
		String password= admin.getAdminPassword();
		List<Admin> alladmin=adminDao.getAllAdmin();
		
		for (Admin admin2 : alladmin) {
			if(decrypt(admin2.getAdminPassword()).equals(password)) {
				logger.info("Admin Found");
				logger.info("Admin is logged in");
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMessage("Admin Found & Granted Access");
				responseStructure.setData(admin2);
				break;
			}
			else {
				throw new WrongEmailIDPasswordException();
			}
		}
		
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.FOUND);
	}
	
	public List<Admin> getAllAdmin(){
		List<Admin> alladmin=adminDao.getAllAdmin();
		return alladmin;
	}

	
	public static String encrypt(String strToEncrypt)
	{
		try {

			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec= new IvParameterSpec(iv);

			SecretKeyFactory factory= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			
			
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(),65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		}
		catch (Exception e) {
			System.out.println("Error while encrypting: "
							+ e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt)
	{
		try {

			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0 };
			
			IvParameterSpec ivspec= new IvParameterSpec(iv);

			SecretKeyFactory factory= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(),65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);
			return new String(cipher.doFinal(
				Base64.getDecoder().decode(strToDecrypt)));
		}
		catch (Exception e) {
			System.out.println("Error while decrypting: "
							+ e.toString());
		}
		return null;
	} 

}
