package com.ty.park_here.service;


import org.apache.log4j.Logger;

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
import com.ty.park_here.exception.UnableToUpdateLocation;
import com.ty.park_here.util.ResponseStructure;

@Service
public class ParkingLocationService {

	@Autowired
	private ParkingLocationDao parkingLocationDao;
//	@Autowired
//	private ParkingSpaceDao parkingSpaceDao;

	public static final Logger logger = Logger.getLogger(ParkingSpaceServices.class);
	
	public ResponseEntity<ResponseStructure<ParkingLocation>> saveParkingLocation(ParkingLocation parkingLocation) {
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Saved ParkingLocation Name");
		responseStructure.setData(parkingLocationDao.saveParkingLocation(parkingLocation));
		logger.debug("Parking location saved");
		return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> updateParkingLocation(ParkingLocation parkingLocation,
			String id) {
		ParkingLocation parkingLocation2 = parkingLocationDao.findById(id);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		if (parkingLocation2 !=null) {
			parkingLocation.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated ParkingLocation name");
			responseStructure.setData(parkingLocationDao.updateParkingLocation(parkingLocation));
			logger.debug("Parking location updated");
			return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}else {
			logger.error("Unable to update parking location");
		throw new UnableToUpdateLocation("Unable to update Parking location as no such id foubd to update");
		}

	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> findById(String id) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(id);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		if (parkingLocation != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName found by Id");
			responseStructure.setData(parkingLocationDao.findById(id));
			logger.debug("Parking location found for givven id");
			return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}else {
			logger.error("Unable to get parking location for given id");
		throw new NoSuchIdFoundException("No such parking location found for given id");
		}
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> findByLocationName(String name) {
		ParkingLocation parkingLocation2 = parkingLocationDao.findByLocationName(name);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		if (parkingLocation2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName found");
			responseStructure.setData(parkingLocationDao.findByLocationName(name));
			logger.debug("Parking location found for givven name");
			return new ResponseEntity<ResponseStructure<ParkingLocation>>(responseStructure, HttpStatus.OK);
		}else {
			logger.error("Unable to get parking location for given id");
		throw new NoSuchNameFoundException("No parking location found for given name");
		}
	}

	public ResponseEntity<ResponseStructure<ParkingLocation>> deleteByLocation(String id) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(id);
		ResponseStructure<ParkingLocation> responseStructure = new ResponseStructure<ParkingLocation>();
		ResponseEntity<ResponseStructure<ParkingLocation>> responseEntity = new ResponseEntity<ResponseStructure<ParkingLocation>>(
				responseStructure, HttpStatus.OK);
		if (parkingLocation != null) {
			parkingLocationDao.deleteByLocation(parkingLocation);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("LocationName deleted");
			responseStructure.setData(parkingLocation);
			logger.warn("Parking location deleted");
			return responseEntity;
		}else {
			logger.error("Unable to delete parking location for given id");
		throw new UnableToDeleteLocationException("No parking location found to delete for given id");
		}
	}

}