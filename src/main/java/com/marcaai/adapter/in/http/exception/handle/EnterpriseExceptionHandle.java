package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.response.ResponseError;
import com.marcaai.core.exception.EnterpriseException;

@RestControllerAdvice
public class EnterpriseExceptionHandle {

	@ExceptionHandler(EnterpriseException.class)
	public ResponseEntity<ResponseError> enterpriseException(EnterpriseException enterpriseException, WebRequest request){
		
		var response = new ResponseError(Arrays.asList(
				enterpriseException.getExceptionEnterpriseType().getMessage()),
				enterpriseException.getExceptionEnterpriseType().toString().toUpperCase(),
				enterpriseException.getExceptionEnterpriseType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
}
