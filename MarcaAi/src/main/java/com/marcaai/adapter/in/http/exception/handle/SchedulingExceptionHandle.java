package com.marcaai.adapter.in.http.exception.handle;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.marcaai.adapter.in.http.exception.response.ResponseError;
import com.marcaai.core.exception.SchedulingException;

@RestControllerAdvice
public class SchedulingExceptionHandle {

	@ExceptionHandler(SchedulingException.class)
	public ResponseEntity<ResponseError> schedulingException(SchedulingException schedulingException, WebRequest request){

		var response = new ResponseError(
				null,
				schedulingException.getExceptionSchedulingType().toString().toUpperCase(),
				schedulingException.getExceptionSchedulingType().getHttpStatus().value(),
				LocalDateTime.now(),
				request.getDescription(false));
	
		if(!schedulingException.getOverlappingSchedules().isEmpty()) {
			response.setMessages(schedulingException.getOverlappingSchedules());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
