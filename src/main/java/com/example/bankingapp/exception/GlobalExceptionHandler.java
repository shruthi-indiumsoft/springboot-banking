package com.example.bankingapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorDetails> handleResourceNotfoundException(ResourceNotFoundException exception,
	    		WebRequest webRequest){
	    	ErrorDetails errorDetails=new ErrorDetails(
	    			LocalDateTime.now(),
	    			exception.getMessage(),
	    			webRequest.getDescription(false),
	    			"USER_NOT_FOUND" );  			
			return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	    	
	    }
}
