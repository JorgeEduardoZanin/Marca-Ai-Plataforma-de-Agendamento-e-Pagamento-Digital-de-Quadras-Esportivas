package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.response.ResponseError;
import com.marcaai.core.exception.AddressException;

@RestControllerAdvice
public class AddressExceptionHandle {

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<ResponseError> addressException(AddressException addressException, WebRequest request){
		var response = new ResponseError(Arrays.asList(
				addressException.getExceptionAddressType().getMessage()),
				addressException.getExceptionAddressType().toString().toUpperCase(),
				addressException.getExceptionAddressType().getHttpStatus().value(),
				LocalDateTime.now(), 
				request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
}
