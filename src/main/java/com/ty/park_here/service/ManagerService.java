package com.ty.park_here.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.ManagerDao;
import com.ty.park_here.dto.Manager;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.UnableToUpdateException;
import com.ty.park_here.util.ResponseStructure;

@Service
public class ManagerService {

	@Autowired
	private ManagerDao managerDao;

	public static final Logger logger = Logger.getLogger(ManagerService.class);

	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager) {
		ResponseStructure<Manager> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Manager details saved successfully");
		responseStructure.setData(managerDao.saveManager(manager));
		logger.debug("Manager saved");
		ResponseEntity<ResponseStructure<Manager>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Manager>> updateManager(Manager manager, int id) {
		Optional<Manager> manager2 = managerDao.getMangerById(id);
		ResponseStructure<Manager> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Manager>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.OK);
		if (manager2.isPresent()) {
			manager.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Manager details updated successfully");
			responseStructure.setData(managerDao.updateManager(manager));
			logger.debug("Manager updated");
			return responseEntity;
		} else {
			logger.error("Unable to update manager for the given id");
			throw new UnableToUpdateException("Unable to update manager as no manager found");
		}
	}

	public ResponseEntity<ResponseStructure<Manager>> getManagerById(int id) {
		Optional<Manager> optional = managerDao.getMangerById(id);
		ResponseStructure<Manager> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Manager>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.OK);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Manager details found");
			responseStructure.setData(managerDao.getMangerById(id).get());
			logger.debug("Manager found");
			return responseEntity;
		} else {
			logger.error("Unable to find manager for the given id");
			throw new NoSuchIdFoundException("No manager found for given id");
		}
	}

	public ResponseEntity<ResponseStructure<Manager>> deleteManagerById(int id) {
		Optional<Manager> optional = managerDao.getMangerById(id);
		ResponseStructure<Manager> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Manager>> responseEntity = new ResponseEntity<ResponseStructure<Manager>>(
				responseStructure, HttpStatus.OK);
		if (optional.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Manager deleted as per given id");
			responseStructure.setData(optional.get());
			logger.warn("Manager deleted");
			return responseEntity;
		} else {
			logger.error("Unable to delete manager for given id");
			throw new NoSuchIdFoundException("No manager found to delete for given id");
		}
	}

}
