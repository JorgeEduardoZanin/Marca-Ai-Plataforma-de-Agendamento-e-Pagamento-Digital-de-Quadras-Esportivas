package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.ResponseError;
import com.marcaai.core.exception.LoginException;

@RestControllerAdvice
public class LoginExceptionHandle {
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ResponseError> loginException(LoginException loginException, WebRequest request){
		var response = new ResponseError(Arrays.asList(
				loginException.getExceptionLoginType().getMessage()),
				loginException.getExceptionLoginType().toString().toUpperCase(),
				loginException.getExceptionLoginType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
