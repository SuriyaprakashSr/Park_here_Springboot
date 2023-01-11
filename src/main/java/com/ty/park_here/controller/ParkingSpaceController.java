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

import com.ty.park_here.dto.ParkingSpace;
import com.ty.park_here.service.ParkingSpaceServices;
import com.ty.park_here.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("parkingspace")
public class ParkingSpaceController {

	@Autowired
	private ParkingSpaceServices parkingSpaceServices;

	@ApiOperation(value = "Save parking space", notes = "It is used to save parking space")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<ParkingSpace>> saveParkingSpace(
			@Valid @RequestBody ParkingSpace parkingSpace, @RequestParam String parkingLocationId) {
		return parkingSpaceServices.saveParkingSpace(parkingSpace, parkingLocationId);
	}

	@ApiOperation(value = "Send Token", notes = "It is used to Send Token")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<ParkingSpace>> SendTocken(@Valid @RequestParam String parkingSpaceId,
			@RequestParam String userId, @RequestParam String parkingLocationId) {
		return parkingSpaceServices.SendToken(parkingSpaceId, userId, parkingLocationId);
	}

	@ApiOperation(value = "update parking space", notes = "It is used to update parking space")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<ResponseStructure<ParkingSpace>> updateParkingSpace(
			@Valid @RequestBody ParkingSpace parkingSpace, @RequestParam String parkingSpaceId) {
		return parkingSpaceServices.updateParkingSpace(parkingSpace, parkingSpaceId);
	}

	@ApiOperation(value = "Get parking space", notes = "It is used to get parking space by name")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<ParkingSpace>> getParkingSpaceByid(
			@Valid @RequestParam String parkingSpaceId) {
		return parkingSpaceServices.getParkingSpaceByid(parkingSpaceId);

	}

	@ApiOperation(value = "Delete parking space", notes = "Use to delete parking space By given Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<String>> deleteParkingSpaceById(@Valid @RequestParam String id) {
		return parkingSpaceServices.deleteParkingSpaceById(id);

	}

}
