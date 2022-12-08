package com.ty.park_here.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.ParkingLocationDao;

@Service
public class ParkingLocationService {

	@Autowired
	private ParkingLocationDao parkingLocation;
}
