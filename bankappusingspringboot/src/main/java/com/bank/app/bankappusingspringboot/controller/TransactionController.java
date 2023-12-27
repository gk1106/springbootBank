package com.bank.app.bankappusingspringboot.controller;




import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dto.Transactions;
import com.bank.app.bankappusingspringboot.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trans")
public class TransactionController
{
	@Autowired
	TransactionService service;
	
     @PostMapping
	public ResponseEntity<ResponseStructure<Transactions>> saveTransactions( @RequestParam String username,@RequestParam String pass,@RequestParam int transtype,@RequestParam double amount)
	{
		return service.saveTransactions(username,pass,transtype,amount);
	}
     @GetMapping("/date")
     public ResponseEntity<ResponseStructure<List<Transactions>>>  findTransactions(@Valid @RequestParam String username,@RequestParam String pass,@RequestParam LocalDate fromdate,@RequestParam LocalDate todate)
     {
    	return service.findbyDateTransactions(username, pass, fromdate,todate);
    			
     }
     @GetMapping
     public ResponseEntity<ResponseStructure<List<Transactions>>>  oneAccountAllTransactions(@Valid @RequestParam String username,@RequestParam String pass)
     {
    	return service.oneAccountAllTransactions(username, pass);
    			
     }
     
    
	

}
