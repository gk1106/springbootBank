package com.bank.app.bankappusingspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.app.bankappusingspringboot.dto.Bank;

public interface BankRepo extends JpaRepository<Bank, Integer>
{
	 @Query("select b from Bank b where b.bankName=?1")
		public Bank getbyBankname(String bankname);

}
