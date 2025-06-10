package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.ResponseError;
import com.marcaai.core.exception.RoleException;

@RestControllerAdvice
public class RoleExceptionHandle {

	@ExceptionHandler(RoleException.class)
	public ResponseEntity<ResponseError> roleException(RoleException roleException, WebRequest request){
		var response = new ResponseError(Arrays.asList(
				roleException.getExceptionRoleType().getMessage()),
				roleException.getExceptionRoleType().toString().toUpperCase(),
				roleException.getExceptionRoleType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
