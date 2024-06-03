package com.sprint.onlineShopping.exception;

import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class OnlineShoppingExceptionAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException recordNotFoundException) {
		return new ResponseEntity<String>(recordNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OnlineShoppingException.class)
	public ResponseEntity<String> handleOnlineShoppingException(OnlineShoppingException onlineShoppingException) {
		System.out.println("OnlineShoppingException");
		return new ResponseEntity<String>(onlineShoppingException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {
		System.out.println("EmptyInputException");
		return new ResponseEntity<String>(emptyInputException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<String> handleJsonMappingException(JsonMappingException jsonMappingException) {
		System.out.println("JsonMappingException");
		return new ResponseEntity<String>("No value present in DB", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		System.out.println("JsonMappingException");
		return new ResponseEntity<String>("No value present in DB", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(
			ConstraintViolationException constraintViolationException) {
		System.out.println("JsonMappingException");
		return new ResponseEntity<String>(constraintViolationException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception) {
		System.out.println("Exception");
		return new ResponseEntity<String>("Records not found in database", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
