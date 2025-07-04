package com.marcaai.core.exception.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionEnterpriseType {

	ENTERPRISE_NOT_FOUND("emrepsa nao encontrada", HttpStatus.NOT_FOUND);
	
	private final String message;
	private final HttpStatus httpStatus;
	
	private ExceptionEnterpriseType(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
	
}
