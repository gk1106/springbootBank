package com.bank.app.bankappusingspringboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dto.Bank;
import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.service.BankService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController 
{
	@Autowired
	BankService service;
	
     @PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@Valid @RequestBody Bank newbank)
	{
		return service.saveBank(newbank);
	}
     @GetMapping
     public ResponseEntity<ResponseStructure<Bank>> findBank(@Valid @RequestParam int id)
     {
    	return service.findBank(id);
     }
     
     
     @GetMapping("/userall")
     public  ResponseEntity<ResponseStructure<List<User>>> FindAllUser(@Valid @RequestParam String email,@RequestParam String pass)
     {
    	 return service.findAllUsers(email, pass);
     }

}
