package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class AccountNotFoundException extends RuntimeException 
{
	private String message ="Account Not Found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
