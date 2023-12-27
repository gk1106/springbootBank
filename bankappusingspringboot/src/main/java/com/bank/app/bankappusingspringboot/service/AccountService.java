package com.bank.app.bankappusingspringboot.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dao.AccountDao;
import com.bank.app.bankappusingspringboot.dao.BankDao;
import com.bank.app.bankappusingspringboot.dao.ManagerDao;
import com.bank.app.bankappusingspringboot.dao.UserDao;
import com.bank.app.bankappusingspringboot.dto.Account;
import com.bank.app.bankappusingspringboot.dto.Accounttype;
import com.bank.app.bankappusingspringboot.dto.Bank;
import com.bank.app.bankappusingspringboot.dto.Manager;
import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.exception.AccountTypeNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerEmailNotFoundException;
import com.bank.app.bankappusingspringboot.exception.ManagerPasswordNotFoundException;
import com.bank.app.bankappusingspringboot.exception.UserNotFoundException;
import com.bank.app.bankappusingspringboot.exception.UserPasswordNotFoundException;

@Service
public class AccountService 
{
	@Autowired
	AccountDao adao;
	@Autowired
	ManagerDao mdao;
	@Autowired
	UserDao udao;
	@Autowired
	BankDao bdao; 
	public ResponseEntity<ResponseStructure<Account>> saveAccount(String name,String pass,String username,Account newaccount,int accounttype) 
	{
		ResponseStructure< Account> str = new ResponseStructure<Account>();
		Manager exmanager=mdao.getbyManagerName(name);
		
		if(exmanager!=null)
		{	
			if(exmanager.getManagerPassword().equals(pass))
		  {
				User exuser= udao.findUserName(username);
				if(exuser!=null)
				{
			       str.setMessage("Manager Permission Grantued Account Created..");
		           str.setStatus(HttpStatus.CREATED.value());
		           
		           if(accounttype==1)
		           {
		        	   newaccount.setAccountType(Accounttype.SAVINGS);
		           }
		           else if(accounttype==2)
		           {
		        	   newaccount.setAccountType(Accounttype.CURRENT);
		           }
		           else if(accounttype==3)
		           {
		        	   newaccount.setAccountType(Accounttype.LOAN);
		           }
		           else 
		           {
		        	   throw new AccountTypeNotFoundException();
		           }
			       newaccount.setUser(exuser);
			       exuser.setAccount(newaccount);
			       str.setData(adao.saveAccount(newaccount));
			       udao.updateUser(exuser, exuser.getUserId());
			       return new  ResponseEntity<ResponseStructure<Account>>(str,HttpStatus.CREATED);
				}
				
				throw new UserNotFoundException();
		  }
		  throw new ManagerPasswordNotFoundException();
	  }
		throw new ManagerEmailNotFoundException();
	}
	public ResponseEntity<ResponseStructure<Account>> findAccount(String username)
	{		
		User finduser = udao.findUserName(username);
		
		ResponseStructure<Account> structure =new ResponseStructure<Account>();
		if(finduser.getAccount()!=null)
		{
			Account findaccount = finduser.getAccount();
		structure.setMessage("Account Found Successfully...");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(adao.findAccount(findaccount.getAccountId()));
		return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFoundException();
		
	}
	public ResponseEntity<ResponseStructure<Account>> deleteAccount(String username,String bankname,String email,String password )
	{
		Manager manager = mdao.managerEmail(email);
		
		
		ResponseStructure<Account> structure =new ResponseStructure<Account>();
		if(manager!=null)
		{		
			if(manager.getManagerPassword().equals(password))
			{
				User useraccount =udao.findUserName(username);
				if(useraccount!=null)
				{
					Bank managerbank=manager.getBank();
		            structure.setMessage("Account Deleted...");
		            structure.setStatus(HttpStatus.OK.value());
		            List<User> userlist=managerbank.getUsers();
		            userlist.remove(useraccount);
		            managerbank.setUsers(userlist);
		            Account removeaccount= useraccount.getAccount();
		            structure.setData(adao.deleteAccount(removeaccount.getAccountId()));
		            bdao.updateBank(managerbank, managerbank.getBankId());
		            return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
	         	}
				throw new UserNotFoundException();
	    }		
			throw new ManagerPasswordNotFoundException();
			
    
   	  }
		throw new ManagerEmailNotFoundException();
 	}
	
	public ResponseEntity<ResponseStructure<Account>> checkBalance(String username,String pass)
	{
		User user=udao.findUserName(username);
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		if(user!=null)
		{
			if(user.getUserPassword().equals(pass))
			{
				Account account = user.getAccount();
				structure.setMessage("User Permession Accpeted...");
				structure.setStatus(HttpStatus.ACCEPTED.value());
				structure.setData(adao.findAccount(account.getAccountId()));
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.ACCEPTED);
			}
			throw new UserNotFoundException();
		}
		
		throw new UserPasswordNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Account>> updateAccounttypesavetocurrent(String manageremail,String managerpass,String username)
	{
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Manager manager=mdao.managerPermission(manageremail, managerpass);
		
		if(manager!=null)
		{
			
			if(manager.getManagerPassword().equals(managerpass))
			{
				User user =udao.findUserName(username);
				
				if(user!=null)
				{
					Account account = user.getAccount();
					
					if(account!=null&&account.getAccountType()==Accounttype.SAVINGS)
					{
						account.setAccountType(Accounttype.CURRENT);
					
						structure.setMessage("Account Type Changed Successfully...");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(adao.updateAccount(account, account.getAccountId()));
						user.setAccount(account);
						udao.updateUser(user, user.getUserId());
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
					}
					else
					{
						structure.setMessage("Please Check The User Has Account or Not and"
								+ " Account Type Only Saving Account Allowed...!!!  ");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(null);
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
					}
				}
				throw new UserNotFoundException();
			}
			else
			{
				structure.setMessage("Please Check Manager Password Does Not Match...!!!");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("Please Check Manager Email Does Not Match...!!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Account>> updateAccounttypecurrenttosave(String manageremail,String managerpass,String username)
	{
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Manager manager=mdao.managerPermission(manageremail, managerpass);
		
		if(manager!=null)
		{
			
			if(manager.getManagerPassword().equals(managerpass))
			{
				User user =udao.findUserName(username);
				
				if(user!=null)
				{
					Account account = user.getAccount();
					
					if(account!=null&&account.getAccountType()==Accounttype.CURRENT)
					{
						account.setAccountType(Accounttype.SAVINGS);
					
						structure.setMessage("Account Type Changed Successfully...");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(adao.updateAccount(account, account.getAccountId()));
						user.setAccount(account);
						udao.updateUser(user, user.getUserId());
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
					}
					else
					{
						structure.setMessage("Please Check The User Has Account or Not and"
								+ " Account Type Only Current Account Allowed...!!!  ");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(null);
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
					}
				}
				throw new UserNotFoundException();
						}
			else
			{
				structure.setMessage("Please Check Manager Password Does Not Match...!!!");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("Please Check Manager Email Does Not Match...!!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<Account>> updateAccounttypesavetoloan(String manageremail,String managerpass,String username)
	{
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Manager manager=mdao.managerPermission(manageremail, managerpass);
		
		if(manager!=null)
		{
			
			if(manager.getManagerPassword().equals(managerpass))
			{
				User user =udao.findUserName(username);
				
				if(user!=null)
				{
					Account account = user.getAccount();
					
					if(account!=null&&account.getAccountType()==Accounttype.SAVINGS)
					{
						account.setAccountType(Accounttype.LOAN);
					
						structure.setMessage("Account Type Changed Successfully...");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(adao.updateAccount(account, account.getAccountId()));
						user.setAccount(account);
						udao.updateUser(user, user.getUserId());
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
					}
					else
					{
						structure.setMessage("Please Check The User Has Account or Not and"
								+ " Account Type Only Saving Account Allowed...!!!  ");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(null);
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
					}
				}
				throw new UserNotFoundException();
			}
			else
			{
				structure.setMessage("Please Check Manager Password Does Not Match...!!!");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("Please Check Manager Email Does Not Match...!!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<Account>> updateAccounttypecurrenttoloan(String manageremail,String managerpass,String username)
	{
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Manager manager=mdao.managerPermission(manageremail, managerpass);
		
		if(manager!=null)
		{
			
			if(manager.getManagerPassword().equals(managerpass))
			{
				User user =udao.findUserName(username);
				
				if(user!=null)
				{
					Account account = user.getAccount();
					
					if(account!=null&&account.getAccountType()==Accounttype.CURRENT)
					{
						account.setAccountType(Accounttype.LOAN);
					
						structure.setMessage("Account Type Changed Successfully...");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(adao.updateAccount(account, account.getAccountId()));
						user.setAccount(account);
						udao.updateUser(user, user.getUserId());
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
					}
					else
					{
						structure.setMessage("Please Check The User Has Account or Not and"
								+ " Account Type Only Current Account Allowed...!!!  ");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(null);
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
					}
				}
				throw new UserNotFoundException();
			}
			else
			{
				structure.setMessage("Please Check Manager Password Does Not Match...!!!");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("Please Check Manager Email Does Not Match...!!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<Account>> updateAccounttypeloantosave(String manageremail,String managerpass,String username)
	{
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Manager manager=mdao.managerPermission(manageremail, managerpass);
		
		if(manager!=null)
		{
			
			if(manager.getManagerPassword().equals(managerpass))
			{
				User user =udao.findUserName(username);
				
				if(user!=null)
				{
					Account account = user.getAccount();
					
					if(account!=null&&account.getAccountType()==Accounttype.LOAN)
					{
						account.setAccountType(Accounttype.SAVINGS);
					
						structure.setMessage("Account Type Changed Successfully...");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(adao.updateAccount(account, account.getAccountId()));
						user.setAccount(account);
						udao.updateUser(user, user.getUserId());
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
					}
					else
					{
						structure.setMessage("Please Check The User Has Account or Not and"
								+ " Account Type Only Loan Account Allowed...!!!  ");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(null);
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
					}
				}
				throw new UserNotFoundException();
			}
			else
			{
				structure.setMessage("Please Check Manager Password Does Not Match...!!!");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("Please Check Manager Email Does Not Match...!!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Account>> updateAccounttypeloantocurrent(String manageremail,String managerpass,String username)
	{
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Manager manager=mdao.managerPermission(manageremail, managerpass);
		
		if(manager!=null)
		{
			
			if(manager.getManagerPassword().equals(managerpass))
			{
				User user =udao.findUserName(username);
				
				if(user!=null)
				{
					Account account = user.getAccount();
					
					if(account!=null&&account.getAccountType()==Accounttype.LOAN)
					{
						account.setAccountType(Accounttype.CURRENT);
					
						structure.setMessage("Account Type Changed Successfully...");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(adao.updateAccount(account, account.getAccountId()));
						user.setAccount(account);
						udao.updateUser(user, user.getUserId());
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.OK);
					}
					else
					{
						structure.setMessage("Please Check The User Has Account or Not and"
								+ " Account Type Only Loan Account Allowed...!!!  ");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(null);
						return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
					}
				}
				throw new UserNotFoundException();
			}
			else
			{
				structure.setMessage("Please Check Manager Password Does Not Match...!!!");
				structure.setStatus(HttpStatus.NOT_FOUND.value());
				structure.setData(null);
				return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			structure.setMessage("Please Check Manager Email Does Not Match...!!!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Account>>(structure,HttpStatus.NOT_FOUND);
		}
	}


}
