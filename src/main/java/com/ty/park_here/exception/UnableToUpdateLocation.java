package com.ty.park_here.exception;

public class UnableToUpdateLocation extends RuntimeException {

	private String message= "Unable to update Location";

	public UnableToUpdateLocation(String message) {
		this.message = message;
	}

	public UnableToUpdateLocation() {
		super();
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
