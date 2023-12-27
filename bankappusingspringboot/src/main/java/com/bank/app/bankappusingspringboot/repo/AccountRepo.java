package com.bank.app.bankappusingspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.app.bankappusingspringboot.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>
{

}
