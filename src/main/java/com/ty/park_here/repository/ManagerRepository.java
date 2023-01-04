package com.ty.park_here.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.park_here.dto.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

}
