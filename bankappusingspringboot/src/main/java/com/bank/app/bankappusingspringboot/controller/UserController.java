package com.bank.app.bankappusingspringboot.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserService service;
	
     @PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestParam String bankname,@RequestBody User newuser)
	{
		return service.saveUser(bankname,newuser);
	}
     @GetMapping
     public ResponseEntity<ResponseStructure<User>> findUser(@Valid @RequestParam String username) 
     {
    	return service.findUser(username);
     }
     @PutMapping
     public  ResponseEntity<ResponseStructure<User>> updateUser( @Valid @RequestBody User updateuser,@RequestParam int id)
     {
    	 return service.updateUser(updateuser, id);
     }
     @DeleteMapping
     public ResponseEntity<ResponseStructure<User>> deleteUser( @Valid @RequestParam String username,@RequestParam String bankname)
     {
    	 return service.deleteUser(username,bankname);
     }
     @PutMapping("/number")
     public ResponseEntity<ResponseStructure<User>> updateContactNumber( @Valid @RequestParam int id,@RequestParam long number)
     {
    	 return service.updateContactNumber(id, number);
     }
     @GetMapping("/loginuser")
     public ResponseEntity<ResponseStructure<User>> userLogin(@Valid @RequestParam String uname,@RequestParam String pass)
     {
    	 return service.userLogin(uname, pass);
     }

}
