package com.ty.park_here.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.park_here.dto.ParkingSpace;
import com.ty.park_here.repository.ParkingSpaceRepository;

@Repository
public class ParkingSpaceDao {

	@Autowired
	private ParkingSpaceRepository parkingSpaceRepository;

	public ParkingSpace SaveParkingSpace(ParkingSpace parkingSpace) {
		return parkingSpaceRepository.save(parkingSpace);
	}

	public ParkingSpace updateParkingSpace(ParkingSpace parkingSpace) {
		return parkingSpaceRepository.save(parkingSpace);
	}

	public ParkingSpace findParkingSpaceByName(String name) {
		return parkingSpaceRepository.findByParkingSpaceName(name);

	}

	public ParkingSpace findParkingSpaceById(int id) {
		Optional<ParkingSpace> optional = parkingSpaceRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}                          

	public void deleteParkingSpace(ParkingSpace parkingSpace) {
		parkingSpaceRepository.delete(parkingSpace);
	}

	

}
