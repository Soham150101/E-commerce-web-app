package com.sprint.onlineShopping.exception;

public class EmptyInputException extends Exception {
	private static final long serialVersionUID = -2881291915759057094L;

	public EmptyInputException() {
		super();
	}

	public EmptyInputException(String message) {
		super(message);
	}

}
