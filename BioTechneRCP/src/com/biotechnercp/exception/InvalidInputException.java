package com.biotechnercp.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {

	private static final String MSG = "The %s is invalid.";
	
	public InvalidInputException(String field) {
		super(String.format(MSG, field));
	}
}
