package com.ty.park_here.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.park_here.dto.User;
import com.ty.park_here.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public Optional<User> getUserById(String id) {
		return repository.findById(id);
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public void deleteUser(User user) {
		repository.delete(user);
	}

}
