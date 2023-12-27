package com.bank.app.bankappusingspringboot.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.app.bankappusingspringboot.dto.Transactions;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer>
{
	@Query("select t from Transactions t where t.date between ?1 and ?2")
	public List<Transactions> findbyDateTransactions(LocalDate fromdate,LocalDate todate);
	
}
