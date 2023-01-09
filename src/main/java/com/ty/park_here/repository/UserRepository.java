package com.ty.park_here.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.park_here.dto.User;

public interface UserRepository extends JpaRepository<User, String>{
//	@Query(value = "SELECT * FROM User ORDER BY id DESC LIMIT 1")
//	public User findUserId();
}
