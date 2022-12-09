package com.ty.park_here.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.park_here.dto.ParkingLocation;
import com.ty.park_here.service.ParkingLocationService;
import com.ty.park_here.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("parkingLocation")
public class ParkingLocationController {

	@Autowired
	private ParkingLocationService parkingLocationService;

	@ApiOperation(value = "Save ParkingLocation", notes = "It is used to save ParkingLocation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found") })

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> saveParkingLocation(
			@RequestBody ParkingLocation parkingLocation) {
		return parkingLocationService.saveParkingLocation(parkingLocation);
	}

	@ApiOperation(value = "Update ParkingLocation", notes = "It is used to update ParkingLocation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found") })

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> updateParkingLocation(
			@RequestBody ParkingLocation parkingLocation, @RequestParam int id) {
		return parkingLocationService.updateParkingLocation(parkingLocation, id);
	}

	@ApiOperation(value = "Find ParkingLocation", notes = "It is used to find ParkingLocation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found") })

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> findById(@RequestParam int id) {
		return parkingLocationService.findById(id);
	}

	@ApiOperation(value = "Delete ParkingLocation", notes = "It is used to delete ParkingLocation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found") })

	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> deleteParkingLocation(@RequestParam int id) {
		return parkingLocationService.deleteByLocation(id);
	}

}
