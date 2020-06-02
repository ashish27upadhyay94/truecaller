package com.truecaller.demo.exception.handler;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5637090573181465772L;

	public RecordNotFoundException(String exception) {
		super(exception);
	}

}
