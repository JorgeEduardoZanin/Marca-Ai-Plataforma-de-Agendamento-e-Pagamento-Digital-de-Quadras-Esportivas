package com.marcaai.core.exception;

import com.marcaai.core.exception.enums.ExceptionRoleType;

public class RoleException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final ExceptionRoleType exceptionRoleType;

	public RoleException(ExceptionRoleType exceptionRoleType) {
		super(exceptionRoleType.getMessage());
		this.exceptionRoleType = exceptionRoleType;
	}

	public ExceptionRoleType getExceptionRoleType() {
		return exceptionRoleType;
	}


	
}
