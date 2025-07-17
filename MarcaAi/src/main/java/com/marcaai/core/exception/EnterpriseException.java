package com.marcaai.core.exception;

import com.marcaai.core.exception.enums.ExceptionEnterpriseType;

public class EnterpriseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final ExceptionEnterpriseType exceptionEnterpriseType;

	public EnterpriseException(ExceptionEnterpriseType exceptionEnterpriseType) {
		super(exceptionEnterpriseType.getMessage());
		this.exceptionEnterpriseType = exceptionEnterpriseType;
	}

	public ExceptionEnterpriseType getExceptionEnterpriseType() {
		return exceptionEnterpriseType;
	}

}
