package com.bank.app.bankappusingspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.app.bankappusingspringboot.dto.User;
import com.bank.app.bankappusingspringboot.repo.UserRepo;
@Repository
public class UserDao 
{
	@Autowired
	UserRepo repo;
	
	
	public User saveUser(User newuser)
	{
		return repo.save(newuser);
	}
	
	public User findUser(int id)
	{
	 Optional<User> optionaluser = repo.findById(id);
	 
	 if(optionaluser.isPresent())
	 {
		 return optionaluser.get();
	 }
	 return null;
	}
	
	public User deleteUser(int id)
	{
		User exituser=findUser(id); 
		if(exituser!=null)
		{
			repo.delete(exituser);
			 return exituser;
		}
		return null;
	}
	public User updateUser(User updateuser,int id)
	{
		User exuser=findUser(id);
		if(exuser!=null)
		{
			updateuser.setUserId(id);
			if(updateuser.getUserName()==null)
			{
				updateuser.setUserName(exuser.getUserName());
			}
			if(updateuser.getUserContact()==0)
			{
				updateuser.setUserContact(exuser.getUserContact());
			}
			if(updateuser.getUserAddress()==null)
			{
				updateuser.setUserAddress(exuser.getUserAddress());
			}
			if(updateuser.getUserPassword()==null)
			{
				updateuser.setUserPassword(exuser.getUserPassword());
			}
			if(updateuser.getAccount()==null)
			{
				updateuser.setAccount(exuser.getAccount());
			}
			repo.save(updateuser);
			return updateuser;
		}
		return null;
	}
	
	public List<User> findAllUser()
	{
		return repo.findAll();
	}
	
	public User findUserName(String username)
	{
		return repo.findUserName(username);
	}
	

	public User loginUsernameandPassword(String username, String userpass) 
	{
		
		return repo.loginUsernameandPassword(username, userpass);
	}
	
}
