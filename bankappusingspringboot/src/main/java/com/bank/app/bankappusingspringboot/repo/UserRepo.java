package com.bank.app.bankappusingspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.app.bankappusingspringboot.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
    @Query("select u from User u where u.userName=?1")
    public User findUserName(String username);

    @Query("select u from User u where u.userName=?1 and u.userPassword=?2")
	public User loginUsernameandPassword(String username,String userpass);
    
}
