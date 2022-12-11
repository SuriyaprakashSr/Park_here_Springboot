package com.ty.park_here.exception;

public class UnableToDeleteLocationException extends RuntimeException{
	
	private String message ="No location found to delete";

	public UnableToDeleteLocationException(String message) {
		
		this.message = message;
	}
	
	public UnableToDeleteLocationException() {
	
	}

	@Override
	public String getMessage() {
		return message;
	}

}
