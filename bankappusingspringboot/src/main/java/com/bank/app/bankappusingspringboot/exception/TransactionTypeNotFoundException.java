package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class TransactionTypeNotFoundException extends RuntimeException
{

	private String message ="TransactionType Not Found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
