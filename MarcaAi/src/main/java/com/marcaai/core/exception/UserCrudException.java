package com.marcaai.core.exception;

import com.marcaai.core.exception.enums.ExceptionUserCrudType;

public class UserCrudException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	private final ExceptionUserCrudType exceptionUserCrudType;

	public UserCrudException(ExceptionUserCrudType exceptionUserCrudType) {
		super(exceptionUserCrudType.getMessage());
		this.exceptionUserCrudType = exceptionUserCrudType;
	}

	public ExceptionUserCrudType getExceptionUserCrudType() {
		return exceptionUserCrudType;
	}
	
}
