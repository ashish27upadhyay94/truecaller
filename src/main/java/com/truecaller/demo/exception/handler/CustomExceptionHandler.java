package com.truecaller.demo.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionMessage exceptionMessages = new ExceptionMessage();
		List<String> errorMessages = new ArrayList<String>();
		exceptionMessages.setPath(((ServletWebRequest) request).getRequest().getServletPath());
		/**
		 * Sending Not found 400 If Input Validations Fails
		 */
		if (ex instanceof MethodArgumentNotValidException) {
			List<FieldError> error = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
			for (FieldError fieldError : error) {
				errorMessages.add(fieldError.getDefaultMessage());
			}
			exceptionMessages.setMessage(errorMessages);
			exceptionMessages.setError(ex.getClass().getCanonicalName());

			return new ResponseEntity<Object>(exceptionMessages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

		/**
		 * Sending Not found 404 If Record Not Found
		 */
		if (ex instanceof RecordNotFoundException) {
			exceptionMessages.setMessage(Arrays.asList(ex.getMessage()));
			exceptionMessages.setError(ex.toString());
			return new ResponseEntity<Object>(exceptionMessages, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}

		if (ex instanceof UserAlreadyExistException) {
			exceptionMessages.setMessage(Arrays.asList(ex.getMessage()));
			exceptionMessages.setError(ex.toString());
			return new ResponseEntity<Object>(exceptionMessages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

		if (ex instanceof UserNotFoundException) {
			exceptionMessages.setMessage(Arrays.asList(ex.getMessage()));
			exceptionMessages.setError(ex.toString());
			return new ResponseEntity<Object>(exceptionMessages, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}

		/**
		 * Sending 500 For Other Exceptions
		 */
		return new ResponseEntity<>(exceptionMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
