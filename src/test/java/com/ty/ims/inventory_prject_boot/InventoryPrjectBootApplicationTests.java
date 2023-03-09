package com.ty.ims.inventory_prject_boot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.ty.ims.inventory_prject_boot.dto.Admin;
import com.ty.ims.inventory_prject_boot.repository.AdminRepository;
import com.ty.ims.inventory_prject_boot.service.AdminService;
import com.ty.ims.inventory_prject_boot.util.ResponseStructure;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryPrjectBootApplicationTests {
	
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	AdminService adminService;
	
	@Test
	@Order(1)
	void contextLoads() {
		System.out.println("testing worked");
	}

	@Test
	@Order(2)
	public void testGetAdminById() {
//		Admin admin = adminRepository.findById(1).get();
		
		ResponseEntity<ResponseStructure<Admin>> admin2=adminService.getAdminById(8);
		Assertions.assertThat(admin2.getStatusCodeValue()).isEqualTo(302);
	}
	
	@Test
	@Order(3)
	public void testSaveAdmin() {
		Admin admin = Admin.builder().adminName("Praveen").adminEmail("praveen@gmail.com").adminPassword("567").adminRole("Sales manager").adminPhone(894346578).build();
		ResponseEntity<ResponseStructure<Admin>> admin2=adminService.saveAdmin(admin);
		Assertions.assertThat(admin2.getStatusCodeValue()).isEqualTo(201);
	}
	
//	@Test
//	@Order(4)
//	public void deleteAdmin() {
//		ResponseEntity<ResponseStructure<Admin>> admin=adminService.deleteAdmin(2);
//		Assertions.assertThat(admin.getStatusCodeValue()).isEqualTo(302);
//	}
//	
//	@Test
//	@Order(5)
//	public void loginAdmin() {
//		Admin admin = Admin.builder().adminEmail("praveen.bag@gmail.com").adminPassword("456").build();
//		ResponseEntity<ResponseStructure<Admin>> admin2= adminService.loginAdmin(admin);
//		Assertions.assertThat(admin2.getStatusCodeValue()).isEqualTo(302);
//		
//	}
	
	
	
	
}
