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
import com.bank.app.bankappusingspringboot.dto.Account;
import com.bank.app.bankappusingspringboot.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController 
{
	@Autowired
	AccountService service;
	
     @PostMapping
	public ResponseEntity<ResponseStructure<Account>> saveAccount(@Valid @RequestParam String name,@RequestParam String pass,@RequestParam String username,@RequestBody Account newaccount,@RequestParam int accounttype)
	{
		return service.saveAccount(name,pass,username,newaccount,accounttype);
	}
     @GetMapping
     public ResponseEntity<ResponseStructure<Account>> findAccount(@Valid @RequestParam String username)
     {
    	return service.findAccount(username);
     }
    
     @DeleteMapping
     public ResponseEntity<ResponseStructure<Account>> deleteAccount( @Valid  @RequestParam String   username,@RequestParam String bankname,@RequestParam String   email,@RequestParam String   pass)
     {
    	 return service.deleteAccount(username,bankname,email,pass);
     }
     @PostMapping("/balance")
     public  ResponseEntity<ResponseStructure<Account>> checkBalance(@Valid @RequestParam String username,@RequestParam String pass)
     {
    	 return service.checkBalance(username, pass);
     }
     @GetMapping("/savetocurrent")
     public ResponseEntity<ResponseStructure<Account>> updateAccounttypesavetocurrent(@Valid @RequestParam String manageremail,@RequestParam String managerpass,@RequestParam String username)
     {
    	 return service.updateAccounttypesavetocurrent(manageremail, managerpass, username);
     }
     
     @GetMapping("/currenttosave")
     public ResponseEntity<ResponseStructure<Account>> updateAccounttypecurrenttosave(@Valid @RequestParam String manageremail,@RequestParam String managerpass,@RequestParam String username)
     {
    	 return service.updateAccounttypecurrenttosave(manageremail, managerpass, username);
     }
     
     @GetMapping("/savetoloan")
     public ResponseEntity<ResponseStructure<Account>> updateAccounttypesavetoloan(@Valid @RequestParam String manageremail,@RequestParam String managerpass,@RequestParam String username)
     {
    	 return service.updateAccounttypesavetoloan(manageremail, managerpass, username);
     }
     
     @PostMapping("/currenttoloan")
     public ResponseEntity<ResponseStructure<Account>> updateAccounttypecurrenttoloan(@Valid @RequestParam String manageremail,@RequestParam String managerpass,@RequestParam String username)
     {
    	 return service.updateAccounttypecurrenttoloan(manageremail, managerpass, username);
     }
     
     @PostMapping("/loantosave")
     public ResponseEntity<ResponseStructure<Account>> updateAccounttypeloantosave(@Valid @RequestParam String manageremail,@RequestParam String managerpass,@RequestParam String username)
     {
    	 return service.updateAccounttypeloantosave(manageremail, managerpass, username);
     }
     @PostMapping("/loantocurrent")
     public ResponseEntity<ResponseStructure<Account>> updateAccounttypeloantocurrent(@Valid @RequestParam String manageremail,@RequestParam String managerpass,@RequestParam String username)
     {
    	 return service.updateAccounttypeloantocurrent(manageremail, managerpass, username);
     }

}

