package com.springboot.banking_system.dto;

import org.springframework.stereotype.Component;

import com.springboot.banking_system.enums.CardType;
import com.springboot.banking_system.enums.InvestmentType;
import com.springboot.banking_system.enums.LoanType;


public class CustomerDto {
	
    private String firstName;
    private String lastName;
    private String accountNumber;
   
	private LoanType LoanType;
    private Double loanAmount;
    private CardType cardType;
    private InvestmentType investmentTypes;
   
    
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
	
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LoanType getLoanType() {
		return LoanType;
	}
	public CustomerDto(String firstName, String lastName, String accountNumber,
			com.springboot.banking_system.enums.LoanType loanType, Double loanAmount, CardType cardType,
			InvestmentType investmentTypes) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		LoanType = loanType;
		this.loanAmount = loanAmount;
		this.cardType = cardType;
		this.investmentTypes = investmentTypes;
	}
	public void setLoanType(LoanType loanType) {
		LoanType = loanType;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public InvestmentType getInvestmentTypes() {
		return investmentTypes;
	}
	public void setInvestmentTypes(InvestmentType investmentTypes) {
		this.investmentTypes = investmentTypes;
	}

}
