package com.wallet.model;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	
	public ApplicationException(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
