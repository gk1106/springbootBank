package com.bank.app.bankappusingspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dao.BankDao;
import com.bank.app.bankappusingspringboot.dao.ManagerDao;
import com.bank.app.bankappusingspringboot.dao.UserDao;
import com.bank.app.bankappusingspringboot.dto.Bank;
import com.bank.app.bankappusingspringboot.dto.Manager;
import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.exception.BankNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerEmailNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerPasswordNotFoundException;

@Service
public class BankService 
{
	@Autowired
	BankDao bdao;
	@Autowired
	ManagerDao mdao;
	@Autowired
	UserDao udao;
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank newbank) 
	{
		ResponseStructure<Bank> structure = new ResponseStructure<Bank>();
		structure.setMessage("Bank Saved Sucessfully...");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(bdao.saveBank(newbank));
		return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.CREATED);
		
	}
	public ResponseEntity<ResponseStructure<Bank>> findBank(int id)
	{
		ResponseStructure<Bank> structure = new ResponseStructure<Bank>();
	     Bank existbank=bdao.findBank(id);
	     if(existbank!=null)
	     {
	    	structure.setMessage("Bank Found Successfully");
	 		structure.setStatus(HttpStatus.FOUND.value());
	 		structure.setData(bdao.findBank(id));
	 		return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.FOUND);
	     }
	     throw new BankNotFoundException(); 
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(String email,String pass)
	{
		ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
	   Manager checkmanager = mdao.managerEmail(email);
		if(checkmanager!=null)
		{
			if(checkmanager.getManagerPassword().equals(pass))
			{
				structure.setMessage("Permission Granted...");
				structure.setStatus(HttpStatus.ACCEPTED.value());
				structure.setData(udao.findAllUser());
				return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.ACCEPTED);
			}
			
			throw new ManagerPasswordNotFoundException();
		}
			throw new ManagerEmailNotFoundException();
		
	}

}
