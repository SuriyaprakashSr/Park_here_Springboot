package com.ty.park_here.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.park_here.dto.ParkingSpace;
import com.ty.park_here.service.ParkingSpaceServices;
import com.ty.park_here.util.ResponseStructure;

@RestController
@RequestMapping("parkingspace")
public class ParkingSpaceController {

	
	@Autowired
	private ParkingSpaceServices parkingSpaceServices;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ParkingSpace>>  saveParkingSpace(@RequestBody ParkingSpace parkingSpace,@RequestParam int id)
	{
	return	parkingSpaceServices.saveParkingSpace(parkingSpace, id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<ParkingSpace>>  updateParkingSpace(@RequestBody ParkingSpace parkingSpace,@RequestParam int id)
	{
	return	parkingSpaceServices.updateParkingSpaces(parkingSpace, id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<ParkingSpace>> getParkingSpaceByName(@RequestParam String name) {
		return  parkingSpaceServices.getParkingSpaceByName(name);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<String>> deleteParkingSpaceById(@RequestParam int id) {
		return parkingSpaceServices.deleteParkingSpaceById(id);
		
	}
	
}
