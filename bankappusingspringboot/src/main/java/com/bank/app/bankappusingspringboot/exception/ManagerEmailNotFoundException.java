package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class ManagerEmailNotFoundException extends RuntimeException
{
	private String message ="Manager Email Not Found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
