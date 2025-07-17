package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.response.ResponseError;
import com.marcaai.core.exception.FootballCourtException;

@RestControllerAdvice
public class FootballCourtExceptionHandle {

	@ExceptionHandler(FootballCourtException.class)
	public ResponseEntity<ResponseError> footballCourtException(FootballCourtException footballCourtException, WebRequest request){
		var response = new ResponseError(Arrays.asList(
				footballCourtException.getExceptionFootballCourtType().getMessage()),
				footballCourtException.getExceptionFootballCourtType().toString().toUpperCase(),
				footballCourtException.getExceptionFootballCourtType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
	
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
