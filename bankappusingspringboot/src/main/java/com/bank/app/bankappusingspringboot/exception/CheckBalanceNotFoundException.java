package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class CheckBalanceNotFoundException extends RuntimeException
{
	private String message ="Low Balance For Your Account ";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
