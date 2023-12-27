package com.bank.app.bankappusingspringboot.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class Bank 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	@NotBlank(message="bank name cannot be blank")
	@NotNull(message="bank name cannot be null")
	private String bankName;
	@NotBlank(message="bank ifsc code cannot be blank")
	@NotNull(message="bank ifsc code cannot be  null")
	private String bankIfsccode;
	@NotBlank(message="bank location cannot be  blank")
	@NotNull(message="bank location cannot be  null")
	private String bankLocation;
	@OneToOne(cascade = CascadeType.ALL)
	private Manager manager;
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> users;
	

}
