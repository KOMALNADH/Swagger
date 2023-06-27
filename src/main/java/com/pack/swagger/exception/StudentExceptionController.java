package com.pack.swagger.exception;

import java.util.Date;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class StudentExceptionController {
	
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}

}
