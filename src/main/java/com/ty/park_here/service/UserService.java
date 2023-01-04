package com.ty.park_here.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.park_here.dao.UserDao;
import com.ty.park_here.dto.User;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.UnableToUpdateException;
import com.ty.park_here.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public static final Logger logger= Logger.getLogger(UserService.class);

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User detail saved Successfully");
		responseStructure.setData(userDao.saveUser(user));
		logger.debug("User saved");
		ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
				responseStructure, HttpStatus.CREATED);
		return responseEntity;
		
	}
	

	public ResponseEntity<ResponseStructure<User>> updateUser(User user, int id) {
		Optional<User> user2 = userDao.getUserById(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
				responseStructure, HttpStatus.OK);
		if (user2.isPresent()) {
			user.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User Updated Successfully");
			responseStructure.setData(userDao.updateUser(user));
			logger.debug("User updated");
			return responseEntity;
		}else
		{
			logger.error("Unable to update user for the given id");
		throw new UnableToUpdateException("Unable to update User as no user found");
		}
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(int id){
		Optional<User> optianl = userDao.getUserById(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		ResponseEntity<ResponseStructure<User>> responseEntity= new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		if (optianl.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User Found");
			responseStructure.setData(userDao.getUserById(id).get());
			logger.debug("User found");
			return responseEntity;
		}else
		{
			logger.error("Unable to find the user for given id");
		throw new NoSuchIdFoundException("No user found for given id");
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		Optional<User> optional= userDao.getUserById(id);
		ResponseStructure<User> responseStructure= new ResponseStructure<User>();
		ResponseEntity<ResponseStructure<User>> responseEntity= new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		if(optional.isPresent()) {
			userDao.deleteUser(optional.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User Deleted as per Given Id");
			responseStructure.setData(optional.get());
			logger.warn("User Deleted");
			return responseEntity;
		}else
		{
			logger.error("unable to delete user for given id");
		throw new NoSuchIdFoundException("No User found to delete for given id");
		}
	}
}
