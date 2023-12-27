package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class ManagerNotFoundException extends RuntimeException
{
	private String message ="manager not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	

}
