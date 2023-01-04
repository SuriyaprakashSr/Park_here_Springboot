package com.ty.park_here.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.park_here.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
