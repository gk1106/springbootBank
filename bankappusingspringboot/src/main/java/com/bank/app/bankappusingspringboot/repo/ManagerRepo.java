package com.bank.app.bankappusingspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.app.bankappusingspringboot.dto.Manager;


public interface ManagerRepo extends JpaRepository<Manager, Integer> 
{

	    @Query("select m from Manager m where m.managerEmailid=?1")
		public Manager getbyManagerEmail(String email);
	    
	    @Query("select m from Manager m where m.managerEmailid=?1 and m.managerPassword=?2")
		public Manager getbyManagerPermission(String email,String pass);
	    
	    @Query("select m from Manager m where m.managerName=?1")
		public Manager getbyManagerName(String name);
}
