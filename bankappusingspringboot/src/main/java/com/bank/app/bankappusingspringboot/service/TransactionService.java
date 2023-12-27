package com.bank.app.bankappusingspringboot.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bank.app.bankappusingspringboot.config.ResponseStructure;
import com.bank.app.bankappusingspringboot.dao.AccountDao;
import com.bank.app.bankappusingspringboot.dao.TransactionsDao;
import com.bank.app.bankappusingspringboot.dao.UserDao;
import com.bank.app.bankappusingspringboot.dto.Account;
import com.bank.app.bankappusingspringboot.dto.Transactions;
import com.bank.app.bankappusingspringboot.dto.Transactionsstatus;
import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.exception.AccountNotFoundException;
import com.bank.app.bankappusingspringboot.exception.CheckBalanceNotFoundException;
import com.bank.app.bankappusingspringboot.exception.TransactionNotFoundException;
import com.bank.app.bankappusingspringboot.exception.UserNotFoundException;
import com.bank.app.bankappusingspringboot.exception.UserPasswordNotFoundException;
@Service
public class TransactionService 
{
	@Autowired
	TransactionsDao tdao;
	@Autowired
	UserDao udao;
	@Autowired
	AccountDao adao;
	
	public ResponseEntity<ResponseStructure<Transactions>> saveTransactions(String username,String pass,int transtype,double transamount) 
	{
		  User   user=udao.findUserName(username);
		  ResponseStructure<Transactions> structure = new  ResponseStructure<Transactions>(); 
		  
		if(user!=null)
		{
			if(user.getUserPassword().equals(pass))
			{
				Account useraccount= user.getAccount();
				if(useraccount!=null)
				{
					Transactions newtransaction = new Transactions();
					
					if(transtype==1)
					{
						newtransaction.setStatus(Transactionsstatus.CREDITED);
						 newtransaction.setDate(LocalDate.now());
						 newtransaction.setTransAmount(transamount);
						 newtransaction.setAccounttrans(useraccount);
						 tdao.saveTransactions(newtransaction);	
						 List<Transactions>translist=useraccount.getTransactions();
						 translist.add(newtransaction);
						 useraccount.setTransactions(translist);
						 useraccount.setAccountBalance(useraccount.getAccountBalance()+ transamount);
						 structure.setMessage("Transaction Completed Amount Crdited.....");
						 structure.setStatus(HttpStatus.CREATED.value());
						 structure.setData(newtransaction);
						 adao.updateAccount(useraccount, useraccount.getAccountId());
						 user.setAccount(useraccount);
						 udao.updateUser(user, user.getUserId());
						 return new ResponseEntity<ResponseStructure<Transactions>>(structure,HttpStatus.ACCEPTED);
					}
					else if(transtype==2)
					{
						if(useraccount.getAccountBalance()>=transamount)
						{
						newtransaction.setStatus(Transactionsstatus.DEBITED);
						 newtransaction.setDate(LocalDate.now());
						 newtransaction.setTransAmount(transamount);
						 newtransaction.setAccounttrans(useraccount);
						 tdao.saveTransactions(newtransaction);
						 List<Transactions>translist=useraccount.getTransactions();
						 translist.add(newtransaction);
						 useraccount.setTransactions(translist);
						 useraccount.setAccountBalance(useraccount.getAccountBalance() - transamount);
						 structure.setMessage("Transaction Completed Amount Debited.....");
						 structure.setStatus(HttpStatus.CREATED.value());
						 structure.setData(newtransaction);
						 adao.updateAccount(useraccount, useraccount.getAccountId());
						 user.setAccount(useraccount);
						 udao.updateUser(user, user.getUserId());
						 return new ResponseEntity<ResponseStructure<Transactions>>(structure,HttpStatus.ACCEPTED);
						}
					 throw new CheckBalanceNotFoundException();
				  }
					throw new TransactionNotFoundException();
				}
			throw new AccountNotFoundException();
		}
		  throw new UserPasswordNotFoundException();
		  
		}
		
		throw new UserNotFoundException();
	
 }
	
	
	public ResponseEntity<ResponseStructure<List<Transactions>>> findbyDateTransactions(String username,String pass,LocalDate fromdate,LocalDate todate)
	{
		ResponseStructure<List<Transactions>> structure = new  ResponseStructure<List<Transactions>>();
		 User user = udao.findUserName(username);
		if(user!=null)
		{
			if(user.getUserPassword().equals(pass))
			{
				   
				    List<Transactions> finddate=tdao.findbyDateTransactions(fromdate,todate);
				  
				    if(finddate.isEmpty()!=true)
				    {	
				    	structure.setMessage("Transactions Found.....");
				    	structure.setStatus(HttpStatus.FOUND.value());
				    	structure.setData(finddate);
				    	return new ResponseEntity<ResponseStructure<List<Transactions>>>(structure,HttpStatus.FOUND);				    	
				    }
				   throw new TransactionNotFoundException();
			}  
		
			throw new UserPasswordNotFoundException();
			
		}
		throw new UserNotFoundException();
	}
	public ResponseEntity<ResponseStructure<List<Transactions>>> oneAccountAllTransactions(String username,String pass)
	{
		ResponseStructure<List<Transactions>> structure = new ResponseStructure<List<Transactions>>();
		User user =udao.findUserName(username);
		if(user!=null)
		{
			if(user.getUserPassword().equals(pass))
			{
				Account  account = user.getAccount();
			  List<Transactions>	alltrans= account.getTransactions();
			 
				  structure.setMessage("Transactions Found....");
				  structure.setStatus(HttpStatus.FOUND.value());
				  structure.setData(alltrans);
				  return new ResponseEntity<ResponseStructure<List<Transactions>>>(structure,HttpStatus.FOUND);
			}
		    throw new UserPasswordNotFoundException();
		}
		throw new UserNotFoundException();
	}
}
