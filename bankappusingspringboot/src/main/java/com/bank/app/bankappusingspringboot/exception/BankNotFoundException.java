package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class BankNotFoundException extends RuntimeException
{
	private String message ="Bank not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
		

}
