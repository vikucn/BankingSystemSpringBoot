package com.springboot.banking_system.dto;

import java.time.LocalDate;


import com.springboot.banking_system.enums.InvestmentType;

public class InvestmentDtoMutualFunds {

	private String firstName;
    private String lastName;
    private String accountNumber;
    
  //investment  
    private InvestmentType investmentType;
	private LocalDate purchaseDate;
	
	
	//mutualfunds
	private String name;
	private int unitsPurchased;
	private double purchasePrice;
	private double amountMutualFunds;
	
	
	public InvestmentDtoMutualFunds(String firstName, String lastName, String accountNumber,
			InvestmentType investmentType, LocalDate purchaseDate, String name, int unitsPurchased,
			double purchasePrice, double amountMutualFunds) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.investmentType = investmentType;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.unitsPurchased = unitsPurchased;
		this.purchasePrice = purchasePrice;
		this.amountMutualFunds = amountMutualFunds;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public InvestmentType getInvestmentType() {
		return investmentType;
	}


	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}


	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getUnitsPurchased() {
		return unitsPurchased;
	}


	public void setUnitsPurchased(int unitsPurchased) {
		this.unitsPurchased = unitsPurchased;
	}


	public double getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	public double getAmountMutualFunds() {
		return amountMutualFunds;
	}


	public void setAmountMutualFunds(double amountMutualFunds) {
		this.amountMutualFunds = amountMutualFunds;
	}
	
	
	
	
	
	
}
