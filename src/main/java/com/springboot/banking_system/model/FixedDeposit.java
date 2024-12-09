package com.springboot.banking_system.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class FixedDeposit {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}

	@Column
	private double depositAmount;
	
	@Column
	private double interestRateFd;
	
	 
	private LocalDate maturityDateFd;
	
	private double amountFd;
	
	public double getAmountFd() {
		return amountFd;
	}

	public void setAmountFd(double amountFd) {
		this.amountFd = amountFd;
	}

	@OneToOne
	private Investment investment;

	public double getInterestRateFd() {
		return interestRateFd;
	}

	public void setInterestRateFd(double interestRateFd) {
		this.interestRateFd = interestRateFd;
	}

	public LocalDate getMaturityDateFd() {
		return maturityDateFd;
	}

	public void setMaturityDateFd(LocalDate maturityDateFd) {
		this.maturityDateFd = maturityDateFd;
	}
	
	
}
