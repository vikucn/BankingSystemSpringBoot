package com.springboot.banking_system.dto;

import java.time.LocalDate;

import com.springboot.banking_system.enums.BondType;
import com.springboot.banking_system.enums.InvestmentType;

public class InvestmentDtoBonds {

	private String firstName;
    private String lastName;
    private String accountNumber;
    
  //investment  
    private InvestmentType investmentType;
	private LocalDate purchaseDate;
	
	
	
	//bonds
	private double faceValue;
	private double interestRate;
	private BondType bondType;
	private LocalDate maturityDate;
	private  double amountBond;
	
	
	
	public InvestmentDtoBonds(String firstName, String lastName, String accountNumber, InvestmentType investmentType,
			LocalDate purchaseDate, double faceValue, double interestRate, BondType bondType, LocalDate maturityDate,
			double amountBond) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.investmentType = investmentType;
		this.purchaseDate = purchaseDate;
		this.faceValue = faceValue;
		this.interestRate = interestRate;
		this.bondType = bondType;
		this.maturityDate = maturityDate;
		this.amountBond = amountBond;
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



	public double getFaceValue() {
		return faceValue;
	}



	public void setFaceValue(double faceValue) {
		this.faceValue = faceValue;
	}



	public double getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}



	public BondType getBondType() {
		return bondType;
	}



	public void setBondType(BondType bondType) {
		this.bondType = bondType;
	}



	public LocalDate getMaturityDate() {
		return maturityDate;
	}



	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}



	public double getAmountBond() {
		return amountBond;
	}



	public void setAmountBond(double amountBond) {
		this.amountBond = amountBond;
	}
	
	
	
}
