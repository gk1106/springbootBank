package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class ManagerPasswordNotFoundException  extends RuntimeException
{
	private String message ="Manager Password Not Found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
