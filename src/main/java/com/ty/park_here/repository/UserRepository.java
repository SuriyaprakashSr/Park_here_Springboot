package com.ty.park_here.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.park_here.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{


}
