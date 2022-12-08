package com.ty.park_here.exception;

public class NoSuchLocationFoundException extends RuntimeException {

	private String message = "Unable To Find Location";

	public NoSuchLocationFoundException(String message) {
		this.message = message;
	}

	public NoSuchLocationFoundException() {

	}

	@Override
	public String getMessage() {
		return message;
	}

}
