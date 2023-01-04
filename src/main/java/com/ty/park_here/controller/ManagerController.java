package com.ty.park_here.controller;

import javax.validation.Valid;

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

import com.ty.park_here.dto.Manager;
import com.ty.park_here.service.ManagerService;
import com.ty.park_here.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@ApiOperation(value = "Save Manager", notes = "It is used to save Manager")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@Valid @RequestBody Manager manager) {
		return managerService.saveManager(manager);
	}

	@ApiOperation(value = "Update Manager", notes = "Used to update Manager")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 405, message = "Method Not Allowed") })
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<ResponseStructure<Manager>> updateManager(@Valid @RequestBody Manager manager,
			@RequestParam int id) {
		return managerService.updateManager(manager, id);
	}

	@ApiOperation(value = "Get Manager", notes = "Get Manager by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Manager>> getManagerById(@Valid @RequestParam int id) {
		return managerService.getManagerById(id);
	}

	@ApiOperation(value = "Delete Manager", notes = "Used to delete Manager By given Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Method Not Allowed") })
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(@Valid @RequestParam int id) {
		return managerService.deleteManagerById(id);

	}
}
