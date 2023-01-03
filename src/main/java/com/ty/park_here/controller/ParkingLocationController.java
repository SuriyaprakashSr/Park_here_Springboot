package com.ty.park_here.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") ,
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed")})

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> saveParkingLocation(@Valid
			@RequestBody ParkingLocation parkingLocation) {
		return parkingLocationService.saveParkingLocation(parkingLocation);
	}

	@ApiOperation(value = "Update ParkingLocation", notes = "It is used to update ParkingLocation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") ,
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed")})

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> updateParkingLocation(@Valid
			@RequestBody ParkingLocation parkingLocation, @RequestParam int id) {
		return parkingLocationService.updateParkingLocation(parkingLocation, id);
	}

	@ApiOperation(value = "Find ParkingLocation", notes = "It is used to find ParkingLocation by name")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> findByName(@Valid @RequestParam String locationName) {
		return parkingLocationService.findByLocationName(locationName);
	}

	@ApiOperation(value = "Delete ParkingLocation", notes = "It is used to delete ParkingLocation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })

	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> deleteParkingLocation(@Valid @RequestParam int id) {
		return parkingLocationService.deleteByLocation(id);
	}

	@ApiOperation(value = "Find ParkingLocation", notes = "It is used to find ParkingLocation by id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "ok") ,
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed")})

	@PatchMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<ParkingLocation>> findById(@Valid @RequestParam int id) {
		return parkingLocationService.findById(id);
	}
}
