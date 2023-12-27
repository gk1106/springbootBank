package com.bank.app.bankappusingspringboot.dto;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Transactions 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transId;
	private double TransAmount;
	@ManyToOne()
	@JsonIgnore
	private Account accounttrans;
	private Transactionsstatus status;
	private LocalDate date;

}
