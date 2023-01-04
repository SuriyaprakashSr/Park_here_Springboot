package com.ty.park_here.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ty.park_here.dto.User;
import com.ty.park_here.repository.UserRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest {

	@Autowired
	UserRepository userRepo;
	
	@Test
	@Order(1)
	void testSaveUser() {
		User user = new User();
		user.setName("kumar");
		user.setEmail("kumar@gmail.com");
		user.setAddress("Blr");
		user.setPassword("ku123");
		user.setPhone(88776645);
		userRepo.save(user);
		assertNotNull(userRepo.findById(1).get());
	}

	@Test
	@Order(2)
	void testGetUserById() {
		User user= userRepo.findById(1).get();
		assertEquals("kumar", user.getName());
	}

	@Test
	@Order(3)
	void testUpdateUser() {
		User user= userRepo.findById(1).get();
		user.setName("kumar");
		userRepo.save(user);
	}

	@Test
	@Order(4)
	void testDeleteUserById() {
		userRepo.deleteById(1);
	}

}
