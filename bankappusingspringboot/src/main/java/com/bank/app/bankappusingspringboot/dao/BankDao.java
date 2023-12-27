package com.bank.app.bankappusingspringboot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankappusingspringboot.dto.Bank;
import com.bank.app.bankappusingspringboot.repo.BankRepo;

@Repository
public class BankDao 
{
	@Autowired
	BankRepo repo;
	
	
	public Bank saveBank(Bank newbank)
	{
		return repo.save(newbank);
	}
	
	public Bank findBank(int id)
	{
	 Optional<Bank> optionalbank = repo.findById(id);
	 
	 if(optionalbank.isPresent())
	 {
		 return optionalbank.get();
	 }
	 return null;
	}
	
	public Bank deleteBank(int id)
	{
		Bank exitbank=findBank(id); 
		if(exitbank!=null)
		{
			repo.delete(exitbank);
			 return exitbank;
		}
		return null;
	}
	public Bank updateBank(Bank updatebank,int id)
	{
		Bank exbank=findBank(id);
		if(exbank!=null)
		{
			updatebank.setBankId(id);
			if(updatebank.getBankName()==null)
			{
				updatebank.setBankName(exbank.getBankName());
			}
			if(updatebank.getBankIfsccode()==null)
			{
				updatebank.setBankIfsccode(exbank.getBankIfsccode());
			}
			if(updatebank.getBankLocation()==null)
			{
				updatebank.setBankLocation(exbank.getBankLocation());
			}
			if(updatebank.getManager()==null)
			{
				updatebank.setManager(exbank.getManager());
			}
			if(updatebank.getUsers()==null)
			{
				updatebank.setUsers(exbank.getUsers());
			}
			repo.save(updatebank);
			return updatebank;
		}
		return null;
	}
	
	public  Bank getBankname(String bankname)
	{
		return repo.getbyBankname(bankname);
	}

}
