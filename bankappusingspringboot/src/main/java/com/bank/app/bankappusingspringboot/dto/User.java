package com.bank.app.bankappusingspringboot.dto;


import org.springframework.stereotype.Component;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class User 
{   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotBlank(message="username cannot be blank")
	@NotNull(message="username cannot be null")
	private String userName;
	@Positive
	@Min(value=6000000000l)
	@Max(value=9999999999l)
	private long userContact;
	@NotNull(message=" useraddress cannot be null")
	@NotBlank(message="useraddress cannot be  blank ")
	private String userAddress;
	@NotNull(message=" userpassword cannot be null")
	@NotBlank(message="userpassword cannot be blank ")
	
	private String userPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Account account;
	

}
