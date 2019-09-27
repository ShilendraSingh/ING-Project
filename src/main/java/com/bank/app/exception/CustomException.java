package com.bank.app.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -7427134941047249166L;

	public CustomException(String message) {
		super(message);

	}
}