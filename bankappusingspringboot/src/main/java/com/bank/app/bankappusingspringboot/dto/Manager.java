package com.bank.app.bankappusingspringboot.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Manager 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	@NotBlank(message="managername cannot be blank")
	@NotNull(message="managername cannot be  null")
	private String managerName;
	@Positive
	@Min(value=6000000000l)
	@Max(value=9999999999l)
	private long managerContact;
	@Email(regexp = "")
	private String managerEmailid;
	@Pattern(regexp="")
	private String managerPassword;
	@OneToOne()
	@JsonIgnore
	private Bank bank;
	

}
