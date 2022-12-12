package com.ty.park_here.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.park_here.dto.ParkingSpace;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {
	

}
