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

import com.ty.park_here.dto.Admin;
import com.ty.park_here.service.AdminService;
import com.ty.park_here.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//API TO SAVE ADMIN
	@ApiOperation(value = "Save Admin", notes = "It is used to save Admin")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@Valid @RequestBody Admin admin){
		return adminService.saveAdmin(admin);
	}
	
	//API TO UPDATE ADMIN
	@ApiOperation(value = "Update Admin", notes = "Uses to update Admin")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 405, message = "Method Not Allowed") })
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@Valid @RequestBody Admin admin, @RequestParam int id) {
		return adminService.updateAdmin(admin, id);
	}
	
	//API TO GET ADMIN
	@ApiOperation(value = "Get Admin", notes = "Get Admin by id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 405, message = "Method Not Allowed") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(@Valid @RequestParam int id){
		return adminService.getAdminById(id);
	}
	
	//API TO DELETE ADMIN
	@ApiOperation(value = "Delete Admin", notes = "Use to delete Admin By given Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Method Not Allowed") })
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@Valid @RequestParam int id) {
		return adminService.deleteAdmin(id);
	}
}
