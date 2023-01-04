package com.ty.park_here.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.AdminDao;
import com.ty.park_here.dto.Admin;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.UnableToUpdateException;
import com.ty.park_here.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public static final Logger logger = Logger.getLogger(AdminService.class);

	// Save Admin
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Admin details saved sucessfully");
		responseStructure.setData(adminDao.saveAdmin(admin));
		logger.debug("Admin saved");
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	// Update Admin
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
		Optional<Admin> admin2 = adminDao.getAdminById(id);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.OK);
		if (admin2.isPresent()) {
			admin.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Admin detail updated");
			responseStructure.setData(adminDao.updateAdmin(admin));
			logger.debug("User updated");
			return responseEntity;
		} else {
			logger.error("Unable to update Admin for the givven id");
			throw new UnableToUpdateException("Unable to update Admin as no Admin found for given id");
		}
	}

	// Get User
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {
		Optional<Admin> admin2 = adminDao.getAdminById(id);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructure, HttpStatus.OK);
		if (admin2.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Admin found for given id");
			responseStructure.setData(adminDao.getAdminById(id).get());
			logger.debug("Admin found");
			return responseEntity;
		} else {
			logger.error("Unable to find Admin for given Id");
			throw new NoSuchIdFoundException("No Admin found for given id");
		}
	}

	// Delete Admin
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
		Optional<Admin> admin2 = adminDao.getAdminById(id);
		ResponseStructure<Admin> responseStructur = new ResponseStructure<Admin>();
		ResponseEntity<ResponseStructure<Admin>> responseEntity = new ResponseEntity<ResponseStructure<Admin>>(
				responseStructur, HttpStatus.OK);
		if(admin2.isPresent()) {
			adminDao.deleteAdmin(admin2.get());
			responseStructur.setStatus(HttpStatus.OK.value());
			responseStructur.setMessage("Admin deleted for provided Id");
			responseStructur.setData(admin2.get());
			logger.warn("Admin deleted");
			return responseEntity;
		}
		else {
			logger.error("No admin found to delete");
			throw new NoSuchIdFoundException("Unable to delete Admin for given id");
		}
	}
}
