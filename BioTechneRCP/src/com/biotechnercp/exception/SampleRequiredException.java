package com.biotechnercp.exception;

@SuppressWarnings("serial")
public class SampleRequiredException extends RuntimeException {
	
	
	public SampleRequiredException() {
		super("The sample must be provided");
	}

}
