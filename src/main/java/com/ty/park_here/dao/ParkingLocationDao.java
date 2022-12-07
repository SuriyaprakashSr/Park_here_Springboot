package com.ty.park_here.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.repository.ParkingLocationRepository;

@Repository
public class ParkingLocationDao {

	@Autowired
	ParkingLocationRepository parkingLocationRepository;

	public ParkingLocation saveParkingLocation(ParkingLocation parkingLocation) {
		return parkingLocationRepository.save(parkingLocation);
	}

	public ParkingLocation updateParkingLocation(ParkingLocation parkingLocation) {
		return parkingLocationRepository.save(parkingLocation);
	}

	public ParkingLocation findByLocationName(String name) {
		return parkingLocationRepository.findByLocationName(name);
	}

	public String deleteByLocationName(int id) {
		parkingLocationRepository.deleteById(id);
		return "deleted";
	}
}
