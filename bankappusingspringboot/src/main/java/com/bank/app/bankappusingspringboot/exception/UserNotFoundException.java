package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException
{
	private String message ="user not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
