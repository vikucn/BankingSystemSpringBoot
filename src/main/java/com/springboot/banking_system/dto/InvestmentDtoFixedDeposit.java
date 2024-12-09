package com.springboot.banking_system.dto;

import java.time.LocalDate;

import com.springboot.banking_system.enums.BondType;
import com.springboot.banking_system.enums.InvestmentType;

public class InvestmentDtoFixedDeposit {
	
	
	//customer
			private String firstName;
		    private String lastName;
		    private String accountNumber;
		    
		  //investment  
		    private InvestmentType investmentType;
			private LocalDate purchaseDate;
			
			
			//fixedDeposit
			private double depositAmount;
			private double interestRateFd;
			private LocalDate maturityDateFd;
			private double amountFd;
			
			
			public InvestmentDtoFixedDeposit(String firstName, String lastName, String accountNumber,
					InvestmentType investmentType, LocalDate purchaseDate, double depositAmount, double interestRateFd,
					LocalDate maturityDateFd, double amountFd) {
				super();
				this.firstName = firstName;
				this.lastName = lastName;
				this.accountNumber = accountNumber;
				this.investmentType = investmentType;
				this.purchaseDate = purchaseDate;
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
