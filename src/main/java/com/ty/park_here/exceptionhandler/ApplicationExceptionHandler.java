package com.ty.park_here.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.park_here.util.ResponseStructure;
import com.ty.park_here.exception.NoSuchIdFoundException;
import com.ty.park_here.exception.NoSuchNameFoundException;
import com.ty.park_here.exception.UnableToUpdateException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchIdFoundException.class)
	public ResponseEntity<ResponseStructure<?>> NoSuchIdFoundException(NoSuchIdFoundException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("No such id found");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<?>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NoSuchNameFoundException.class)
	public ResponseEntity<ResponseStructure<?>> NoSuchNameFoundException(NoSuchNameFoundException exception){
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("No Such Name Found");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<?>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnableToUpdateException.class)
	public ResponseEntity<ResponseStructure<?>> UnableToUpdateException(UnableToUpdateException exception){
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Unable to update ");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<?>>(responseStructure,HttpStatus.NOT_FOUND); 
	}

}
