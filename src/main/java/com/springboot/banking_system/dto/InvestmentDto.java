package com.springboot.banking_system.dto;

import java.time.LocalDate;

import com.springboot.banking_system.enums.BondType;
import com.springboot.banking_system.enums.InvestmentType;



public class InvestmentDto {
//customer
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
		
		
		//mutualfunds
		private String name;
		private int unitsPurchased;
		private double purchasePrice;
		private double amountMutualFunds;
		
		
		//fixedDeposit
		private double depositAmount;
		private double interestRateFd;
		private LocalDate maturityDateFd;
		private double amountFd;
		


		public InvestmentDto(String firstName, String lastName, String accountNumber, InvestmentType investmentType,
				LocalDate purchaseDate, double faceValue, double interestRate, BondType bondType,
				LocalDate maturityDate, double amountBond, String name, int unitsPurchased, double purchasePrice,
				double amountMutualFunds, double depositAmount, double interestRateFd, LocalDate maturityDateFd,
				double amountFd) {
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
			this.name = name;
			this.unitsPurchased = unitsPurchased;
			this.purchasePrice = purchasePrice;
			this.amountMutualFunds = amountMutualFunds;
			this.depositAmount = depositAmount;
			this.interestRateFd = interestRateFd;
			this.maturityDateFd = maturityDateFd;
			this.amountFd = amountFd;
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

		public double getDepositAmount() {
			return depositAmount;
		}

		public void setDepositAmount(double depositAmount) {
			this.depositAmount = depositAmount;
		}

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

		public double getAmountFd() {
			return amountFd;
		}

		public void setAmountFd(double amountFd) {
			this.amountFd = amountFd;
		}
		
		
		
		
		
		
	
}