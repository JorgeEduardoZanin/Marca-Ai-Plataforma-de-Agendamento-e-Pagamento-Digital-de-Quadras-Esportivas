package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.response.ResponseError;
import com.marcaai.core.exception.UserCrudException;

public class UserCrudExceptionHandle {

	@ExceptionHandler(UserCrudException.class)
	public ResponseEntity<ResponseError> userCrudException(UserCrudException userCrudException, WebRequest request){
		var response = new ResponseError(Arrays.asList(
				userCrudException.getExceptionUserCrudType().getMessage()),
				userCrudException.getExceptionUserCrudType().toString().toUpperCase(),
				userCrudException.getExceptionUserCrudType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
