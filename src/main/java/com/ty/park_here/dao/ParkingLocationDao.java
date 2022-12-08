package com.ty.park_here.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.repository.ParkingLocationRepository;

@Repository
public class ParkingLocationDao {

	@Autowired
	private ParkingLocationRepository parkingLocationRepository;

	public ParkingLocation saveParkingLocation(ParkingLocation parkingLocation) {
		return parkingLocationRepository.save(parkingLocation);
	}

	public ParkingLocation updateParkingLocation(ParkingLocation parkingLocation) {
		return parkingLocationRepository.save(parkingLocation);
	}

	public ParkingLocation findByLocationName(String name) {
		return parkingLocationRepository.findByLocationName(name);
	}

	public ParkingLocation findById(int id) {
		return parkingLocationRepository.findById(id).get();
	}

	public void deleteByLocation(ParkingLocation parkingLocation) {
		parkingLocationRepository.delete(parkingLocation);
	}
}
