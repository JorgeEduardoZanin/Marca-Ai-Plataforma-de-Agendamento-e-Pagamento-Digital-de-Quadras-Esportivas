package com.marcaai.core.exception;

import com.marcaai.core.exception.enums.ExceptionCompanyOwnerType;

public class CompanyOwnerException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final ExceptionCompanyOwnerType exceptionCompanyOwnerType;

	public CompanyOwnerException(ExceptionCompanyOwnerType exceptionCompanyOwnerType) {
		super(exceptionCompanyOwnerType.getMessage());
		this.exceptionCompanyOwnerType = exceptionCompanyOwnerType;
	}

	public ExceptionCompanyOwnerType getExceptionCompanyOwnerType() {
		return exceptionCompanyOwnerType;
	}

	
}
