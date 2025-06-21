package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.marcaai.adapter.in.http.exception.response.ResponseError;

@RestControllerAdvice
public class GlobalExceptionsHandle extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		var errors = ex.getFieldErrors().stream()
				.map(item -> item.getField() + " " + item.getDefaultMessage())
				.toList();
		
		var response = new ResponseError(
				errors,
				"ERROR_ATTRIBUTE_VALIDATION",
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now(),
				request.getDescription(false)
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
