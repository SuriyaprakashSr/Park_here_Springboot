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

	public User getUserById(int id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public User updateUser(User user, int id) {
		return repository.save(user);
	}

	public String deleteUSer(int id) {
		repository.deleteById(id);
		return "Deleted";
	}

}
