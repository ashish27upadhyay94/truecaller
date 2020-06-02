package com.truecaller.demo.exception.handler;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8191462404602125491L;

	public UserNotFoundException(String exception) {
		super(exception);
	}
}
