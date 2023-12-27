package com.bank.app.bankappusingspringboot.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.app.bankappusingspringboot.config.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler
{
	@ExceptionHandler
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex)
	{
		ResponseStructure<Object> structure = new ResponseStructure<Object>();
		Map<String, String> hashmap = new HashMap<String, String>();
		
		for(ConstraintViolation<?> violation : ex.getConstraintViolations() )
		{
			String field = violation.getPropertyPath().toString();
			String message =violation.getMessage();
			hashmap.put(field, message);
		}
		
		structure.setMessage("add proper details....");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(hashmap);
		return new ResponseEntity<Object>(structure,HttpStatus.FORBIDDEN);
	}
	protected ResponseEntity<Object> handleMethodArgumentNotValidResponseEntity(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus ststus, WebRequest request)
	{
		List<ObjectError> list = ex.getAllErrors();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for(ObjectError error :list)
		{
			String message = error.getDefaultMessage();
			String fieldname = ((FieldError)error).getField();
			hashmap.put(message, fieldname);
		}
		return new ResponseEntity<Object> (hashmap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> UserNotFound(UserNotFoundException e)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(e.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("user not found with given username");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> UserPasswordNotFound(UserPasswordNotFoundException upe)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(upe.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("user not found with given userpassword");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BankNotFound(BankNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("bank not found with given bankname");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ManagerNotFound(ManagerNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("manager not found with given manager name");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ManagerEmailNotFound(ManagerEmailNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("manager email not found with given manager email");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ManagerPasswordNotFound(ManagerPasswordNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("manager password not found with given manager password");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AccountNotFound(AccountNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Account not found with given username");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AccountTypeNotFound(AccountTypeNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("accounttype not found with given accounttype");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> CheckBalanceNotFound(CheckBalanceNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("check balance not found with given account");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TransactionNotFound(TransactionNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Transaction not found with given Account");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TransactiontypeNotFound(TransactionTypeNotFoundException bex)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(bex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("TransactionType not found with given TransactionType");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}

}
