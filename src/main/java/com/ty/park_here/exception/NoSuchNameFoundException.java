package com.ty.park_here.exception;

public class NoSuchNameFoundException extends RuntimeException {
	
	private String message ="no such name found";

	public NoSuchNameFoundException(String message) {
		
		this.message = message;
	}
	
	public NoSuchNameFoundException() {
	
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	

}
