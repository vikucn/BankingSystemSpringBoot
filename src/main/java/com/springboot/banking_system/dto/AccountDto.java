package com.springboot.banking_system.dto;

import java.time.LocalDate;

import com.springboot.banking_system.enums.AccountType;
import com.springboot.banking_system.enums.CardType;
import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.enums.InvestmentType;
import com.springboot.banking_system.enums.LoanType;


public class AccountDto {

//	Account
	private String accountNumber;
	private AccountType accountType;
	private double balance;
	private String aadharNumber;
	private String panNumber;
	
	//Customer
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String contactNumber;
	private String email;
	
	
//Loan
	private String purpose;
	private LoanType LoanType;
	private double amount;
	private LocalDate dateCreated;
	private String status;
	private double interestRate;
	
   
  //investment
	
	private InvestmentType investmentType;
	private LocalDate purchaseDate;
	private InvestmentStatus investmentStatus;
	
	
//card
	private String cardNumber;
	private CardType cardType;
    private LocalDate expiryDate;
    
    
	public AccountDto(String accountNumber, AccountType accountType, double balance, String aadharNumber,
			String panNumber, String firstName, String lastName, LocalDate dateOfBirth, String gender,
			String contactNumber, String email, String purpose, com.springboot.banking_system.enums.LoanType loanType,
			double amount, LocalDate dateCreated, String status, double interestRate, InvestmentType investmentType,
			LocalDate purchaseDate, InvestmentStatus investmentStatus, String cardNumber, CardType cardType,
			LocalDate expiryDate) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.email = email;
		this.purpose = purpose;
		LoanType = loanType;
		this.amount = amount;
		this.dateCreated = dateCreated;
		this.status = status;
		this.interestRate = interestRate;
		this.investmentType = investmentType;
		this.purchaseDate = purchaseDate;
		this.investmentStatus = investmentStatus;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public LoanType getLoanType() {
		return LoanType;
	}
	public void setLoanType(LoanType loanType) {
		LoanType = loanType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
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
	public InvestmentStatus getInvestmentStatus() {
		return investmentStatus;
	}
	public void setInvestmentStatus(InvestmentStatus investmentStatus) {
		this.investmentStatus = investmentStatus;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	
	
}
