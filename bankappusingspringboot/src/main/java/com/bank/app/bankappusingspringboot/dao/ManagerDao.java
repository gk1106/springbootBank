package com.bank.app.bankappusingspringboot.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankappusingspringboot.dto.Manager;
import com.bank.app.bankappusingspringboot.repo.ManagerRepo;

@Repository
public class ManagerDao 
{
	@Autowired
	ManagerRepo repo;
	
	
	public Manager saveManager(Manager newmanager)
	{
		return repo.save(newmanager);
	}
	
	public Manager findManager(int id)
	{
	 Optional<Manager> optionalmanager = repo.findById(id);
	 
	 if(optionalmanager.isPresent())
	 {
		 return optionalmanager.get();
	 }
	 return null;
	}
	
	public Manager deleteManager(int id)
	{
		Manager exitmanager=findManager(id); 
		if(exitmanager!=null)
		{
			repo.delete(exitmanager);
			 return exitmanager;
		}
		return null;
	}
	public Manager updateManager(Manager updatemanager,int id)
	{
		Manager exmanager=findManager(id);
		if(exmanager!=null)
		{
			updatemanager.setManagerId(id);
			if(updatemanager.getManagerName()==null)
			{
				updatemanager.setManagerName(exmanager.getManagerName());
			}
			if(updatemanager.getManagerContact()==0)
			{
				updatemanager.setManagerContact(exmanager.getManagerContact());
			}
			if(updatemanager.getManagerEmailid()==null)
			{
				updatemanager.setManagerEmailid(exmanager.getManagerEmailid());
			}
			if(updatemanager.getManagerPassword()==null)
			{
				updatemanager.setManagerPassword(exmanager.getManagerPassword());
			}
			if(updatemanager.getBank()==null)
			{
				updatemanager.setBank(exmanager.getBank());
			}
			repo.save(updatemanager);
			return updatemanager;
		}
		return null;
	}
	
	public List<Manager> findAllManager()
	{
		return repo.findAll();
	}
	
	public Manager managerEmail(String email)
	{
		return repo.getbyManagerEmail(email);
	}
	
	public Manager managerPermission(String email,String pass)
	{
		return repo.getbyManagerPermission(email,pass);
	}
	public Manager getbyManagerName(String name)
	{
		return repo.getbyManagerName(name);
	}



}
