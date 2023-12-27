package com.bank.app.bankappusingspringboot.dao;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankappusingspringboot.dto.Transactions;
import com.bank.app.bankappusingspringboot.repo.TransactionsRepo;

@Repository
public class TransactionsDao 
{
	@Autowired
	TransactionsRepo repo;
	
	
	public Transactions saveTransactions(Transactions newtransactions)
	{
		return repo.save(newtransactions);
	}
	
	public Transactions findTransactions(int id)
	{
	 Optional<Transactions> optionaltransactions = repo.findById(id);
	 
	 if(optionaltransactions.isPresent())
	 {
		 return optionaltransactions.get();
	 }
	 return null;
	}
	
	
	
	
	public List<Transactions> findAllTransactions()
	{
		return repo.findAll();
	}
	
	public List<Transactions> findbyDateTransactions( LocalDate fromdate,LocalDate todate)
	{
		return repo.findbyDateTransactions(fromdate,todate);
	}

}
