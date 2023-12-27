package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class TransactionNotFoundException extends RuntimeException
{
	private String message ="Transaction Not Found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
