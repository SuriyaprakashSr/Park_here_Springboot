
package com.ty.park_here.service;

import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.ParkingLocationDao;
import com.ty.park_here.dao.ParkingSpaceDao;
import com.ty.park_here.dao.UserDao;
import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.dto.ParkingSpace;
import com.ty.park_here.dto.User;
import com.ty.park_here.emailsender.SendEmail;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.NoSuchLocationFoundException;
import com.ty.park_here.exception.UnableToUpdateException;
import com.ty.park_here.util.ResponseStructure;

@Service
public class ParkingSpaceServices {
	@Autowired
	ParkingSpaceDao parkingSpaceDao;
	@Autowired
	ParkingLocationDao parkingLocationDao;
	@Autowired
	SendEmail sendEmail;
	@Autowired
	UserDao userDao;

	public static final Logger logger = Logger.getLogger(ParkingSpaceServices.class);

	public ResponseEntity<ResponseStructure<ParkingSpace>> saveParkingSpace(ParkingSpace parkingSpace,
			String parkingSpaceId) {
		ParkingLocation location = parkingLocationDao.findById(parkingSpaceId);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (location != null) {
			List<ParkingSpace> list = location.getParkingSpaces();
			list.add(parkingSpace);
			parkingSpace.setAvailableSpace((parkingSpace.getTotalSpace()) - (parkingSpace.getUtilizedSpace()));
			location.setParkingSpaces(list);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("parking Spaces Added Sucessfully");
			responseStructure.setData(parkingSpaceDao.saveParkingSpace(parkingSpace));
			parkingLocationDao.saveParkingLocation(location);
			logger.debug("Parking Space Saved");
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.CREATED);
		} else {
			logger.error("Unable to save parking space");
			throw new NoSuchLocationFoundException("unable to save parking space because no such location found");
		}
	}

	public ResponseEntity<ResponseStructure<ParkingSpace>> SendToken(String parkingSpaceId, String userId,
			String parkingLocationId) {
		ParkingSpace parkingSpace1 = parkingSpaceDao.findParkingSpaceById(parkingSpaceId);
		ParkingLocation location = parkingLocationDao.findById(parkingLocationId);
		LocalTime time = LocalTime.now();
		User user1 = userDao.getUserById(userId).get();
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (parkingSpace1 != null) {
			String body = "Vehicle number: " + user1.getVehicleNo() + "\nName: " + user1.getName()
					+ "\nParking location: " + location.getLocationName() + "\nParking Space name: "
					+ parkingSpace1.getParkingSpaceName() + "\nParking Rent per hour: " + parkingSpace1.getRentPerHour()
					+ "\nParking time is: " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
			sendEmail.sendMail(user1.getEmail(), body, "Parking detail of your vehicle");
//			parkingSpace.setParkingSpaceId(parkingSpaceId);
//			parkingSpace.setAvailableSpace(parkingSpace.getTotalSpace() - parkingSpace.getUtilizedSpace());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Token sent sucessfully to " + user1.getEmail());
			responseStructure.setData(parkingSpace1);
			logger.debug("Token has been sent sucessfully");
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.OK);
		} else {
			logger.error("Unable to senď token");
			throw new UnableToUpdateException();
		}
	}

	public ResponseEntity<ResponseStructure<ParkingSpace>> updateParkingSpace(ParkingSpace parkingSpace,
			String parkingSpaceId) {
		ParkingSpace parkingSpace1 = parkingSpaceDao.findParkingSpaceById(parkingSpaceId);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (parkingSpace1 != null) {
			parkingSpace.setParkingSpaceId(parkingSpaceId);
			parkingSpace.setAvailableSpace(parkingSpace.getTotalSpace() - parkingSpace.getUtilizedSpace());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Parking spaces updated Sucessfully");
			responseStructure.setData(parkingSpaceDao.updateParkingSpace(parkingSpace));
			logger.debug("Parking Space Updated");
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.OK);
		} else {
			logger.error("Unable to update parking space");
			throw new UnableToUpdateException();
		}
	}

	public ResponseEntity<ResponseStructure<ParkingSpace>> getParkingSpaceByid(String parkingSpaceId) {
		ParkingSpace parkingSpace = parkingSpaceDao.findParkingSpaceById(parkingSpaceId);
		ResponseStructure<ParkingSpace> responseStructure = new ResponseStructure<>();
		if (parkingSpace != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Parking spaces received sucessfully");
			responseStructure.setData(parkingSpaceDao.findParkingSpaceById(parkingSpaceId));
			logger.debug("Parking Space found");
			return new ResponseEntity<ResponseStructure<ParkingSpace>>(responseStructure, HttpStatus.OK);
		} else {
			logger.error("Unable to find parking space");
			throw new NoSuchIdFoundException("no such id found");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteParkingSpaceById(String parkingSpaceId) {
		ParkingSpace parkingSpace = parkingSpaceDao.findParkingSpaceById(parkingSpaceId);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		if (parkingSpace != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("ParkingSpace Deleted  Sucessfully");
			responseStructure.setData(parkingSpaceDao.deleteParkingSpace(parkingSpaceId));
			logger.warn("Parking Space found");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

		} else {
			logger.error("Unable to delete parking space");
			throw new NoSuchIdFoundException();
		}
	}

}
