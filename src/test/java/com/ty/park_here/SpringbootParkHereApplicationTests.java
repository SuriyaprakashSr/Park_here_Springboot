package com.ty.park_here;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ty.park_here.dao.UserDao;
import com.ty.park_here.dto.User;
import com.ty.park_here.repository.UserRepository;

@SpringBootTest
class SpringbootParkHereApplicationTests {

	@MockBean
	private UserRepository repository;

	@Autowired
	private UserDao dao;

	Optional<User> optional;

	@BeforeEach
	public void optionalTest() {
		User user = new User();
		user.setId(1);
		user.setName("Kumar Rishabh");
		user.setEmail("rishabh.kumar0@gmail.com");
		user.setPassword("rishabh");
		user.setPhone(8102114064l);
		user.setRole("Admin");
		optional = Optional.of(user);
	}

	@Test
	public void saveUserTest() {
		User user = optional.get();
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, dao.saveUser(user));
	}

	@Test
	public void getUserByIdTest() {
		Optional<User> user = Optional.of(optional.get());
		when(repository.findById(1)).thenReturn(user);
		assertEquals(user, dao.getUserById(1));
	}

}
