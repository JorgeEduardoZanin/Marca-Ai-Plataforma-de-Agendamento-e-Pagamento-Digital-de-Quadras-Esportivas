package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.response.ResponseError;
import com.marcaai.core.exception.CompanyOwnerException;

@RestControllerAdvice
public class CompanyOwnerExceptionHandle {

	@ExceptionHandler(CompanyOwnerException.class)
	public ResponseEntity<ResponseError> companyOwnerException(CompanyOwnerException companyOwnerException, WebRequest request){
		
		var response = new ResponseError(Arrays.asList(
				companyOwnerException.getExceptionCompanyOwnerType().getMessage()),
				companyOwnerException.getExceptionCompanyOwnerType().toString().toUpperCase(),
				companyOwnerException.getExceptionCompanyOwnerType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
}
