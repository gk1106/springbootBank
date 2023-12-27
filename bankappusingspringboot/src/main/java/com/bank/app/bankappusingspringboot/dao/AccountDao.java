package com.bank.app.bankappusingspringboot.dao;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankappusingspringboot.dto.Account;
import com.bank.app.bankappusingspringboot.repo.AccountRepo;

@Repository
public class AccountDao 
{
	@Autowired
	AccountRepo repo;
	
	
	public Account saveAccount(Account newaccount)
	{
		return repo.save(newaccount);
	}
	
	public Account findAccount(int id)
	{
	 Optional<Account> optionalaccount = repo.findById(id);
	 
	 if(optionalaccount.isPresent())
	 {
		 return optionalaccount.get();
	 }
	 return null;
	}
	
	public Account deleteAccount(int id)
	{
		Account exitaccount=findAccount(id); 
		if(exitaccount!=null)
		{
			repo.delete(exitaccount);
			 return exitaccount;
		}
		return null;
	}
	public Account updateAccount(Account updateaccount,int id)
	{
		Account exaccount=findAccount(id);
		if(exaccount!=null)
		{
			updateaccount.setAccountId(id);
			if(updateaccount.getAccountNumber()==0)
			{
				updateaccount.setAccountNumber(exaccount.getAccountNumber());
			}
			if(updateaccount.getAccountType()==null)
			{
				updateaccount.setAccountType(exaccount.getAccountType());
			}
			if(updateaccount.getAccountBalance()==null)
			{
				updateaccount.setAccountBalance(exaccount.getAccountBalance());
			}
			if(updateaccount.getUser()==null)
			{
				updateaccount.setUser(exaccount.getUser());
			}
			if(updateaccount.getTransactions()==null)
			{
				updateaccount.setTransactions(exaccount.getTransactions());
			}
			repo.save(updateaccount);
			return updateaccount;
		}
		return null;
	}
	

}
