package com.menagerie.crm.exceptions;

import java.util.Date;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value=PropertyValueException.class)
	public ResponseEntity<Object> handleNullpointer(PropertyValueException ex, WebRequest request){
		String errorMessageDiscription=ex.getLocalizedMessage();
		if (errorMessageDiscription == null) {
			errorMessageDiscription=ex.toString();
		}
		ErrorMessage em = new ErrorMessage(new Date(),errorMessageDiscription);
		return new ResponseEntity<>(em,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}

}
