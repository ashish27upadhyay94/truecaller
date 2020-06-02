package com.truecaller.demo.exception.handler;

public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 5479044105609266027L;

	public UserAlreadyExistException(String message) {
		super(message);
	}

}
