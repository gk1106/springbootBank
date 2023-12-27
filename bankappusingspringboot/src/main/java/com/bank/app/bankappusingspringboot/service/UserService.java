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
import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.exception.BankNotFoundException;
import com.bank.app.bankappusingspringboot.exception.UserNotFoundException;
import com.bank.app.bankappusingspringboot.exception.UserPasswordNotFoundException;
@Service
public class UserService 
{
	@Autowired
	UserDao udao;
	@Autowired
	ManagerDao mdao;
	@Autowired
	BankDao bdao;
	
	
	public ResponseEntity<ResponseStructure<User>> saveUser(String bankname,User newuser) 
	{
		ResponseStructure<User> structure = new ResponseStructure<User>();
		Bank exibank =bdao.getBankname(bankname);
	if(exibank!=null)
	 {
		structure.setMessage("User Saved Successfully...");
		structure.setStatus(HttpStatus.CREATED.value());
		List<User> userlist =exibank.getUsers();
		userlist.add(newuser);
		exibank.setUsers(userlist);
		structure.setData(udao.saveUser(newuser));
		bdao.updateBank(exibank, exibank.getBankId());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	 }

	throw new BankNotFoundException();
 }
	
	
	public ResponseEntity<ResponseStructure<User>> findUser(String username)    
	{		
		User finduser = udao.findUserName(username);
		ResponseStructure<User> structure =new ResponseStructure<User>();
		if(finduser!=null)
		{		
		structure.setMessage("User Found Successfully...");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(finduser);
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFoundException();
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User  updateuser,int id)
	{
		ResponseStructure<User> structure = new ResponseStructure<User>();
		structure.setMessage("User Updated Successfully...");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(udao.updateUser(updateuser, id));
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
	
	}
	public ResponseEntity<ResponseStructure<User>> deleteUser(String name,String bankname)
	{
		ResponseStructure<User> structure = new ResponseStructure<User>();
		User exuser =udao.findUserName(name);
		Bank exbank =bdao.getBankname(bankname);
		if(exuser!=null)
		{
			if(exbank!=null)
			{
				List<User> userlist =exbank.getUsers();
				userlist.remove(exuser);
				exbank.setUsers(userlist);
				structure.setMessage("User Deleted Successfully...");
				structure.setStatus(HttpStatus.OK.value());
				User removeuser=udao.deleteUser(exuser.getUserId());
				structure.setData(removeuser);
				bdao.updateBank(exbank, exbank.getBankId());
				return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
			}
			else
			{
				
				throw new BankNotFoundException();
			}
		}
		throw new UserNotFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<User>> updateContactNumber(int id,long number)
	{
		User exuser = udao.findUser(id);
		exuser.setUserContact(number);
		
		ResponseStructure<User> structure = new ResponseStructure<User>();
		structure.setMessage("User Updated Successfully...");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(udao.updateUser(exuser, id));
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
	
	}
	public ResponseEntity<ResponseStructure<User>> userLogin(String uname,String userpass)
	{
		  User username = udao.loginUsernameandPassword(uname,userpass);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if(username!=null)
		{
			if(username.getUserPassword().equals(userpass))
			{
				structure.setMessage("UserName and Password Valid...");
				structure.setData(username);
				structure.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);	
			}
			
				throw new UserPasswordNotFoundException();
			
	      }
		throw new UserNotFoundException();
	}

}
