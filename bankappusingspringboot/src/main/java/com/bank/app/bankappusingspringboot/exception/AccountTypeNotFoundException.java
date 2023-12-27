package com.bank.app.bankappusingspringboot.exception;

@SuppressWarnings("serial")
public class AccountTypeNotFoundException  extends RuntimeException
{
	private String message ="AccountType not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
