package com.ty.park_here.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.park_here.dto.ParkingLocation;

public interface ParkingLocationRepository extends JpaRepository<ParkingLocation, Integer> {

	public ParkingLocation findByLocationName(String name);

}
