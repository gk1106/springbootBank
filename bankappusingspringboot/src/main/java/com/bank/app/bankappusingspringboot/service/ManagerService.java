package com.bank.app.bankappusingspringboot.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dao.BankDao;
import com.bank.app.bankappusingspringboot.dao.ManagerDao;
import com.bank.app.bankappusingspringboot.dto.Bank;
import com.bank.app.bankappusingspringboot.dto.Manager;
import com.bank.app.bankappusingspringboot.exception.BankNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerEmailNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerPasswordNotFoundException;
@Service
public class ManagerService 
{
	@Autowired
	ManagerDao mdao;
	@Autowired
	BankDao bdao;
	
	public ResponseEntity<ResponseStructure<Manager>> saveManager(String bankname,Manager newmanager) 
	{
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
		Bank exibank = bdao.getBankname(bankname);
		if(exibank!=null)
	   {
		
		  structure.setMessage("Manager Saved Successfully...");
		  structure.setStatus(HttpStatus.CREATED.value());
		  newmanager.setBank(exibank);
	   	  exibank.setManager(newmanager);
		  structure.setData(mdao.saveManager(newmanager));
	 	  bdao.updateBank(exibank, exibank.getBankId());
		  return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.CREATED);
		}
		 throw new BankNotFoundException();
		
	}
	public  ResponseEntity<ResponseStructure<Manager>> findManager(int id)
	{
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
	    Manager exmanger = mdao.findManager(id);
	    if(exmanger!=null)
	    {
	    	structure.setMessage("Manager Found Successfully...");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(mdao.findManager(id));
			return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.FOUND);
	    	
	    }
	   throw new ManagerNotFoundException();
	}
	public ResponseEntity<ResponseStructure<Manager>> updateManager(Manager  updatemanager,int id)
	{
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
		structure.setMessage("Manager Updated Successfully...");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(mdao.updateManager(updatemanager, id));
		return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.OK);
	
	}
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int id)
	{
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
	  Manager removemanager = mdao.deleteManager(id);
	  if(removemanager!=null)
	  {
		    structure.setMessage("Manager Information  Deleted...");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(removemanager);
			return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.OK);
	  }
	 throw new ManagerNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Manager>> managerLogin(String email,String password)
	{
		Manager manageremail=mdao.managerEmail(email);
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
		if(manageremail!=null)
		{
			if(manageremail.getManagerPassword().equals(password))
			{
				structure.setMessage("Manager Email Id and Password Valid");
				structure.setData(manageremail);
				structure.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Manager>>(structure,HttpStatus.ACCEPTED);	
			}
			throw new ManagerPasswordNotFoundException(); 
		}
		throw new ManagerEmailNotFoundException();
	}

}
