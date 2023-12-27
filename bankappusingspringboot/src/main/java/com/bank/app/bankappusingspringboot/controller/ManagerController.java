package com.bank.app.bankappusingspringboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dto.Manager;
import com.bank.app.bankappusingspringboot.service.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController 
{
	@Autowired
	ManagerService service;
	
     @PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@Valid @RequestParam String bankname,@RequestBody Manager newmanager)
	{
		return service.saveManager(bankname,newmanager);
	}
     @GetMapping
     public ResponseEntity<ResponseStructure<Manager>> findManager(@Valid @RequestParam int id)
     {
    	return service.findManager(id);
     }
     
     @DeleteMapping
     public ResponseEntity<ResponseStructure<Manager>> deleteManager(@Valid @RequestParam int  id)
     {
    	 return service.deleteManager(id);
     }
     
     @PostMapping("/login")
     public ResponseEntity<ResponseStructure<Manager>> userLogin(@Valid @RequestParam String email,@RequestParam String pass)
     {
    	 return service.managerLogin(email, pass);
     }

}
