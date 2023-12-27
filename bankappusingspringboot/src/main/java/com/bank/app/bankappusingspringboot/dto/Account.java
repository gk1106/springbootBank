package com.bank.app.bankappusingspringboot.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	@Positive
	@Max(value=100000000000l)
	private long accountNumber;
	private Accounttype accountType;
	@Positive(message="only numbers")
	private Double accountBalance;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transactions> transactions;

}
