package com.springboot.banking_system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.banking_system.dto.AccountDto;
import com.springboot.banking_system.dto.CustomerDto;
import com.springboot.banking_system.dto.InvestmentDto;
import com.springboot.banking_system.dto.InvestmentDtoBonds;
import com.springboot.banking_system.dto.InvestmentDtoFixedDeposit;
import com.springboot.banking_system.dto.InvestmentDtoMutualFunds;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c where c.id = ?1")
	List<Customer> getCustomerDetail(int id);

	
//	@Query("SELECT c FROM Customer c JOIN c.Account a JOIN a.Investment i JOIN a.Loan l JOIN a.Card cr")
	
	
	
	    @Query("SELECT new com.springboot.banking_system.dto.CustomerDto(c.firstName, c.lastName, a.accountNumber, " +
	           "l.LoanType, l.amount, " +
	           "card.cardType, inv.investmentType) " +
	           "FROM Account a " +
	           "JOIN a.customer c " +
	           "LEFT JOIN Loan l ON l.account.id = a.id " +
	           "LEFT JOIN Card card ON card.account.id = a.id " +
	           "LEFT JOIN Investment inv ON inv.account.id = a.id ")
	    List<CustomerDto> getCustomerAllDetails();

	    @Query("SELECT new com.springboot.banking_system.dto.CustomerDto(c.firstName, c.lastName, a.accountNumber, " +
		           "l.LoanType, l.amount, " +
		           "card.cardType, inv.investmentType) " +
		           "FROM Account a " +
		           "JOIN a.customer c " +
		           "LEFT JOIN Loan l ON l.account.id = a.id " +
		           "LEFT JOIN Card card ON card.account.id = a.id " +
		           "LEFT JOIN Investment inv ON inv.account.id = a.id where a.accountNumber=?1")
		List<CustomerDto> getCustomerDetailsByAccNum(String accNumber);

	    @Query("SELECT new com.springboot.banking_system.dto.AccountDto( " +
	    	       "a.accountNumber, a.accountType, a.balance, a.aadharNumber, a.panNumber, " +
	    	       "c.firstName, c.lastName, c.dateOfBirth, c.gender, c.contactNumber, c.email, " +
	    	       "l.purpose, l.LoanType, l.amount, l.dateCreated, l.status, l.interestRate, " +
	    	       "inv.investmentType, inv.purchaseDate, inv.investmentStatus, " +
	    	       "card.cardNumber, card.cardType, card.expiryDate) " +
	    	       "FROM Account a " +
	    	       "JOIN a.customer c " +
	    	       "LEFT JOIN Loan l ON l.account.id = a.id " +
	    	       "LEFT JOIN Card card ON card.account.id = a.id " +
	    	       "LEFT JOIN Investment inv ON inv.account.id = a.id " +
	    	       "WHERE a.accountNumber =?1")
	    List<AccountDto> getAccountDtoDetails(String accountNumber);


//	    @Query("select c from Account a join a.customer c left join Investment i on i.account.id=a.id join Bonds b on b.investment.id=i.id where b.id=?1")
	   
	    
	    
//	    @Query("SELECT c FROM Bonds b " +
//	    	       "JOIN b.investment i " +
//	    	       "JOIN i.account a " +
//	    	       "JOIN a.customer c " +
//	    	       "WHERE b.id = ?1")
//	    List<InvestmentDto> showCustomersInBond(int id);
	    
//	    @Query("SELECT new com.springboot.banking_system.dto.InvestmentDtoBonds(" +
//	    	       "c.firstName, c.lastName, a.accountNumber, " +
//	    	       "i.investmentType, i.purchaseDate, " +
//	    	       "b.faceValue, b.interestRate, b.bondType, b.maturityDate, b.amountBond) " +
//	    	       "FROM Bonds b " +
//	    	       "JOIN b.investment i " +
//	    	       "JOIN i.account a " +
//	    	       "JOIN a.customer c " +
//	    	       "WHERE b.id = ?1")
//	    	List<InvestmentDtoBonds> showCustomersInBond(int id);
	    @Query("SELECT new com.springboot.banking_system.dto.InvestmentDtoBonds(" +
	    	       "c.firstName, c.lastName, a.accountNumber, " +
	    	       "i.investmentType, i.purchaseDate, " +
	    	       "b.faceValue, b.interestRate, b.bondType, b.maturityDate, b.amountBond) " +
	    	       "FROM Bonds b " +
	    	       "JOIN b.investment i " +
	    	       "JOIN i.account a " +
	    	       "JOIN a.customer c " +
	    	       "WHERE b.id = ?1")
	    	List<InvestmentDtoBonds> showCustomersInBond(int id);



//	    @Query("SELECT c FROM MutualFunds b " +
//	    	       "JOIN b.investment i " +
//	    	       "JOIN i.account a " +
//	    	       "JOIN a.customer c " +
//	    	       "WHERE b.id = ?1")
//		List<InvestmentDto> showCustomersInMutualFunds(int id);
//	    @Query("SELECT new com.springboot.banking_system.dto.InvestmentDtoMutualFunds(" +
//	    	       "c.firstName, c.lastName, a.accountNumber, " +
//	    	       "i.investmentType, i.purchaseDate, " +
//	    	       "b.name, b.unitsPurchased, b.purchasePrice, b.amountMutualFunds) " +
//	    	       "FROM MutualFunds b " +
//	    	       "JOIN b.investment i " +
//	    	       "JOIN i.account a " +
//	    	       "JOIN a.customer c " +
//	    	       "WHERE b.id = ?1")
//	    	List<InvestmentDtoMutualFunds> showCustomersInMutualFunds(int id);
	    @Query("SELECT new com.springboot.banking_system.dto.InvestmentDtoMutualFunds(" +
	    	       "c.firstName, c.lastName, a.accountNumber, " +
	    	       "i.investmentType, i.purchaseDate, " +
	    	       "m.name, m.unitsPurchased, m.purchasePrice, m.amountMutualFunds) " +
	    	       "FROM MutualFunds m " +
	    	       "JOIN m.investment i " +
	    	       "JOIN i.account a " +
	    	       "JOIN a.customer c " +
	    	       "WHERE m.id = ?1")
	    	List<InvestmentDtoMutualFunds> showCustomersInMutualFunds(int id);



//	    @Query("SELECT c FROM FixedDeposit b " +
//	    	       "JOIN b.investment i " +
//	    	       "JOIN i.account a " +
//	    	       "JOIN a.customer c " +
//	    	       "WHERE b.id = ?1")
//		List<InvestmentDto> showCustomersInFixedDeposit(int id);
//	
	    
	    @Query("SELECT new com.springboot.banking_system.dto.InvestmentDtoFixedDeposit(" +
	    	       "c.firstName, c.lastName, a.accountNumber, " +
	    	       "i.investmentType, i.purchaseDate, " +
	    	       "b.depositAmount, b.interestRateFd, b.maturityDateFd, b.amountFd) " +
	    	       "FROM FixedDeposit b " +
	    	       "JOIN b.investment i " +
	    	       "JOIN i.account a " +
	    	       "JOIN a.customer c " +
	    	       "WHERE b.id = ?1")
	    	List<InvestmentDtoFixedDeposit> showCustomersInFixedDeposit(int id);

	    @Query("SELECT new com.springboot.banking_system.dto.CustomerDto(c.firstName, c.lastName, a.accountNumber, " +
		           "l.LoanType, l.amount, " +
		           "card.cardType, inv.investmentType) " +
		           "FROM Account a " +
		           "JOIN a.customer c " +
		           "LEFT JOIN Loan l ON l.account.id = a.id " +
		           "LEFT JOIN Card card ON card.account.id = a.id " +
		           "LEFT JOIN Investment inv ON inv.account.id = a.id ")
		Page<CustomerDto> getCustomerDetails(Pageable pageable);







}
