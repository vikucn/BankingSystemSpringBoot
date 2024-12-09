package com.springboot.banking_system.exception;

public class InvalidUsernameException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	private String message;

	public InvalidUsernameException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
	
}
