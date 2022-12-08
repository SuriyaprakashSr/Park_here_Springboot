package com.ty.park_here.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.ParkingLocationDao;
import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.exception.NoSuchNameFoundException;
import com.ty.park_here.exception.UnableToUpdateException;
import com.ty.park_here.util.ResponseStructure;

@Service
public class ParkingLocationService {

	@Autowired
	private ParkingLocationDao parkingLocationDao;

	public ResponseEntity<ResponseStructure<ParkingLocation>> saveParkingLocation(ParkingLocation parkingLocation) {
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Saved ParkingLocation Name");
		responseStructure.setData(parkingLocationDao.saveParkingLocation(parkingLocation));
		return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> updateParkingLocation(ParkingLocation parkingLocation,
			String name) {
		ParkingLocation parkingLocation2 = parkingLocationDao.updateParkingLocation(parkingLocation);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<ParkingLocation>> entity;
		if (parkingLocation2 != null) {
			parkingLocation.setLocationName(name);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated ParkingLocation name");
			responseStructure.setData(parkingLocationDao.updateParkingLocation(parkingLocation));
			return entity = new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}
		throw new UnableToUpdateException();
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> findByLocationName(String name) {
		ParkingLocation parkingLocation2 = parkingLocationDao.findByLocationName(name);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<ParkingLocation>> entity;
		if (parkingLocation2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName found");
			responseStructure.setData(parkingLocationDao.findByLocationName(name));
			return entity = new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchNameFoundException();
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> deleteByLocationName(String name) {
		ParkingLocation optional = parkingLocationDao.findByLocationName(name);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<ParkingLocation>> responseEntity = new ResponseEntity<ResponseStructure<ParkingLocation>>(
				responseStructure, HttpStatus.OK);
		if (optional != null) {
			parkingLocationDao.deleteByLocationName(optional);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("No LocationName found");
			responseStructure.setData(optional);
			return responseEntity;
		}
		throw new NoSuchNameFoundException();
	}

}
