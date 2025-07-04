package com.marcaai.core.exception;

import com.marcaai.core.exception.enums.ExceptionAddressType;

public class AddressException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final ExceptionAddressType exceptionAddressType;

	public AddressException(ExceptionAddressType exceptionAddressType) {
		super(exceptionAddressType.getMessage());
		this.exceptionAddressType = exceptionAddressType;
	}

	public ExceptionAddressType getExceptionAddressType() {
		return exceptionAddressType;
	}
	
}
