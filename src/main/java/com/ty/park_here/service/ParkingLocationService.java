package com.ty.park_here.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.ParkingLocationDao;
import com.ty.park_here.dao.ParkingSpaceDao;
import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.NoSuchNameFoundException;
import com.ty.park_here.exception.UnableToDeleteLocationException;
import com.ty.park_here.exception.UnableToUpdateException;
import com.ty.park_here.exception.UnableToUpdateLocation;
import com.ty.park_here.util.ResponseStructure;

@Service
public class ParkingLocationService {

	@Autowired
	private ParkingLocationDao parkingLocationDao;
	@Autowired
	private ParkingSpaceDao parkingSpaceDao;

	public ResponseEntity<ResponseStructure<ParkingLocation>> saveParkingLocation(ParkingLocation parkingLocation) {
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Saved ParkingLocation Name");
		responseStructure.setData(parkingLocationDao.saveParkingLocation(parkingLocation));
		return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> updateParkingLocation(ParkingLocation parkingLocation,
			int id) {
		ParkingLocation parkingLocation2 = parkingLocationDao.findById(id);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		if (parkingLocation2 != null) {
			parkingLocation.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated ParkingLocation name");
			responseStructure.setData(parkingLocationDao.updateParkingLocation(parkingLocation));
			return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}
		throw new UnableToUpdateLocation("Unable to update Parking location as no such id foubd to update");
		
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> findById(int id) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(id);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		if (parkingLocation != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName found by Id");
			responseStructure.setData(parkingLocationDao.findById(id));
			return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchIdFoundException("No such parking location found for given id");
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> findByLocationName(String name) {
		ParkingLocation parkingLocation2 = parkingLocationDao.findByLocationName(name);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		if (parkingLocation2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName found");
			responseStructure.setData(parkingLocationDao.findByLocationName(name));
			return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchNameFoundException("No parking location found for given name");
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> deleteByLocation(int id) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(id);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		ResponseEntity<ResponseStructure<ParkingLocation>> responseEntity = new ResponseEntity<ResponseStructure<ParkingLocation>>(
				responseStructure, HttpStatus.OK);
		if (parkingLocation != null) {
			parkingLocationDao.deleteByLocation(parkingLocation);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName deleted");
			responseStructure.setData(parkingLocation);
			return responseEntity;
		}
		throw new UnableToDeleteLocationException("No parking location found to delete for given id");
	}

}