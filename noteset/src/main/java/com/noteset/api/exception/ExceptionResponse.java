package com.noteset.api.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private String message;
	private String error;
	private Integer status;
	
	public ExceptionResponse() {
		super();
	}
	
	public ExceptionResponse(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.error = httpStatus.name();
		this.status = httpStatus.value();
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
