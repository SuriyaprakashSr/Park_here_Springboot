package com.ty.park_here.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.ParkingLocationDao;
import com.ty.park_here.dao.ParkingSpaceDao;
import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.dto.ParkingSpace;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.NoSuchNameFoundException;
import com.ty.park_here.util.ResponseStructure;

@Service
public class ParkingSpaceServices {
	@Autowired
	ParkingSpaceDao parkingSpaceDao;
	@Autowired
	ParkingLocationDao parkingLocationDao;

	public ResponseEntity<ResponseStructure<ParkingSpace>> saveParkingSpace(ParkingSpace parkingSpace, int id) {
		ParkingLocation location = parkingLocationDao.findById(id);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		List<ParkingSpace> list = location.getParkingSpaces();
		if (location != null) {
			list.add(parkingSpace);
			location.setParkingSpaces(list);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("parking Spaces Added Sucessfully");
			responseStructure.setData(parkingSpaceDao.saveParkingSpace(parkingSpace));
			parkingLocationDao.saveParkingLocation(location);
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.CREATED);
		}
		return null;

	}

	public ResponseEntity<ResponseStructure<ParkingSpace>> updateParkingSpaces(ParkingSpace parkingSpace, int id) {
		ParkingSpace parkingSpace1 = parkingSpaceDao.findParkingSpaceById(id);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (parkingSpace1 != null) {
			parkingSpace.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Parking spaces updated Sucessfully");
			responseStructure.setData(parkingSpaceDao.updateParkingSpace(parkingSpace));
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchIdFoundException();

	}

	public ResponseEntity<ResponseStructure<ParkingSpace>> getParkingSpaceByName(String name) {
		ParkingSpace parkingSpace = parkingSpaceDao.findParkingSpaceByName(name);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (parkingSpace != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Parking spaces received sucessfully");
			responseStructure.setData(parkingSpace);
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.OK);
		}
		throw new NoSuchNameFoundException();

	}

	public ResponseEntity<ResponseStructure<ParkingSpace>> deleteParkingSpaceById(int id) {
		ParkingSpace parkingSpace = parkingSpaceDao.findParkingSpaceById(id);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (parkingSpace != null) {
			parkingSpaceDao.deleteParkingSpace(parkingSpace);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("ParkingSpace Deleted  Sucessfully");
			responseStructure.setData(parkingSpace);
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.OK);

		}
		throw new NoSuchIdFoundException();

	}

}
