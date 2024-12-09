package com.springboot.banking_system.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.banking_system.dto.AccountStatementDto;
import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Card;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Loan;
import com.springboot.banking_system.model.Transaction;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.service.AccountService;
import com.springboot.banking_system.service.CardService;
import com.springboot.banking_system.service.CustomerService;
import com.springboot.banking_system.service.LoanService;
import com.springboot.banking_system.service.TransactionService;
//import com.springboot.banking_system.service.TransactionService;
import com.springboot.banking_system.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private CardService cardService;
	
	
	@PostMapping("/customer/register/{uid}")
	public ResponseEntity<?> registerCustomer(@PathVariable int uid,@RequestBody Customer customer,ResponseMessageDto dto) {
		// its used to insert the customer details i.e. to register customer
		User user = null;
		try {
			user = userService.validate(uid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
			}
		
		customer.setUser(user);
		 customer =  customerService.insert(customer);	
		 return ResponseEntity.ok(customer);
		 
	}
	
	@PutMapping("/customer/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id,@RequestBody Customer newCustomer ,ResponseMessageDto dto) {
		
		// validate id
		try {
			Customer existingCustomerDb = customerService.validate(id);
			if(newCustomer.getContactNumber()!=null)
				existingCustomerDb.setContactNumber(newCustomer.getContactNumber());
			if(newCustomer.getAddress()!=null)
				existingCustomerDb.setAddress(newCustomer.getAddress());
			if(newCustomer.getAadharNumber()!=null)
				existingCustomerDb.setAadharNumber(newCustomer.getAadharNumber());
			if(newCustomer.getEmail()!=null)
				existingCustomerDb.setEmail(newCustomer.getEmail());
			if(newCustomer.getFirstName()!=null)
				existingCustomerDb.setFirstName(newCustomer.getFirstName());
			if(newCustomer.getLastName()!=null) 
				existingCustomerDb.setLastName(newCustomer.getLastName());
			if(newCustomer.getPanNumber()!=null)
				existingCustomerDb.setPanNumber(newCustomer.getPanNumber());
			

			//re save this existing customer having new updated value 
			existingCustomerDb = customerService.insert(existingCustomerDb);
			return ResponseEntity.ok(existingCustomerDb);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
			
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id,ResponseMessageDto dto) {
		
		try {
			customerService.validate(id);// to validate the id  given
			customerService.delete(id); // to delete the id given
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		dto.setMsg("Customer deleted");
		return ResponseEntity.ok(dto);
		
	}
	
	@GetMapping("/customer/detail/{id}")
	public ResponseEntity<?> getCustomerDetail(@PathVariable int id,ResponseMessageDto dto) {
		
		try {
			customerService.validate(id); // to validate id
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<Customer> list =  customerService.getCustomerDetail(id);
		return ResponseEntity.ok(list);
		
		
	}
	
	

	
	
	
	
	// account - Ops;
	
	@PostMapping("/customer/account/add/{cid}")
	public ResponseEntity<?> addAccount(@PathVariable int cid,@RequestBody Account accountDet,Account account ,ResponseMessageDto dto) {
		
		// validate customer id first;
		Customer customer = null;
	
	try {
		customer = customerService.validate(cid);
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	account.setCustomer(customer);
	account.setAccountType(accountDet.getAccountType());
	account.setBalance(accountDet.getBalance());
	account.setDateCreated(LocalDate.now());
	
	account.setAadharNumber(accountDet.getAadharNumber());
	account.setPanNumber(accountDet.getPanNumber());
	
//	System.out.println(account.getAadharNumber());
	account = accountService.insert(account);
//	System.out.println(account.getAadharNumber());
	return ResponseEntity.ok(account);	
	}
	
	
	@GetMapping("/customer/account/details/{cid}")
	public ResponseEntity<?> getAccountDetails(@PathVariable int cid,ResponseMessageDto dto) {
		
		try {
			customerService.validate(cid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<Account> list = accountService.getAccountDetails(cid);
		return ResponseEntity.ok(list);
		
	}
	
	@PutMapping("/customer/account/update/{aid}")
	public ResponseEntity<?> updateCustomerAccount(@PathVariable int aid,@RequestBody Account newAccount ,ResponseMessageDto dto) {
		
		// validate id
		try {
			Account existingAccountDb = accountService.validate(aid);
			if(newAccount.getAadharNumber()!=null)
				existingAccountDb.setAadharNumber(newAccount.getAadharNumber());
			if(newAccount.getPanNumber()!=null)
				existingAccountDb.setPanNumber(newAccount.getPanNumber());
			

			//re save this existing customer having new updated value 
			existingAccountDb = accountService.insertUpdatedData(existingAccountDb);
			return ResponseEntity.ok(existingAccountDb);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
			
	}
	
	
	
	// transactions ops;
	
	// to deposit money
	@PostMapping("/customer/account/deposit/{aid}/{amount}")
	public ResponseEntity<?> depositMoney(@PathVariable int aid,@PathVariable double amount,ResponseMessageDto dto) {
		
		// validate account id;
		Account account= null;
		     try {
				account = accountService.validateIdAndAmount(aid,amount);
			} catch (ResourceNotFoundException e) {
				dto.setMsg(e.getMessage());
				return ResponseEntity.badRequest().body(dto);
			} 
		    
		     
		     Transaction transaction = transactionService.depositMoney(aid,amount);
		     return ResponseEntity.ok(transaction);
		
		
	}
	
	// to withdraw money
	@PostMapping("/customer/account/withdraw/{aid}/{amount}")
	public ResponseEntity<?> withdrawMoney(@PathVariable int aid,@PathVariable double amount,ResponseMessageDto dto) {
		
		// validate account id;
		Account account= null;
		     try {
				account = accountService.validateIdAndAmount(aid,amount);
			} catch (ResourceNotFoundException e) {
				dto.setMsg(e.getMessage());
				return ResponseEntity.badRequest().body(dto);
			} 
		    
		     
		     Transaction transaction = transactionService.withdrawMoney(aid,amount);
		     return ResponseEntity.ok(transaction);
		
		
	}
	
	// to transfer money;
	@PostMapping("/customer/account/transfer/{aid}/{reaccno}/{amount}")
	public ResponseEntity<?> transferMoney(@PathVariable int aid,@PathVariable String reaccno,@PathVariable double amount,ResponseMessageDto dto) {
		// validate account id;
		Account account= null;
			 try {
				 account = accountService.validateIdAndAmountAndBalance(aid,amount,reaccno);
			} catch (ResourceNotFoundException e) {
				dto.setMsg(e.getMessage());
				return ResponseEntity.badRequest().body(dto);
			} 
//			 System.out.println(reaccno);
			 Transaction transaction = transactionService.transferMoney(aid,reaccno,amount);
			
			 return ResponseEntity.ok(transaction);
	}
	
	
	@GetMapping("/customer/account/transaction/{aid}")
	public ResponseEntity<?> getTransactionHistory(@PathVariable int aid,ResponseMessageDto dto) {

		try {
			accountService.validate(aid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<Transaction> list = accountService.getTransactionHistory(aid);
		return ResponseEntity.ok(list);
		
		
	}
	
	
	// loan ops
	
	@PostMapping("/customer/account/loan/add/{aid}")
	public ResponseEntity<?> addLoan(@PathVariable int aid,@RequestBody Loan loanDet ,Loan loan,ResponseMessageDto dto) {
		// validate account id first;
		Account account = null;
	
	try {
		account = accountService.validate(aid);
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	loan.setLoanType(loanDet.getLoanType());
	loan.setPurpose(loanDet.getPurpose());
	loan.setAmount(loanDet.getAmount());
	loan.setStatus("Not Aprroved");
	loan.setAccount(account);
	
	loan = loanService.insert(loan);
	return ResponseEntity.ok(loan);
	
	
	
		
	}
	
	@GetMapping("/customer/account/loan/detail/{aid}")
	public ResponseEntity<?> getLoanDetails(@PathVariable int aid,ResponseMessageDto dto) {
		
		try {
			accountService.validate(aid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<Loan> list = loanService.getLoanDetails(aid);
		return ResponseEntity.ok(list);
		
	}
	
	
	//  card ops
	
	@PostMapping("/customer/account/card/add/{aid}")
	public ResponseEntity<?> addcard(@PathVariable int aid,@RequestBody Card cardDet ,Card card,ResponseMessageDto dto) {
		// validate account id first;
		Account account = null;
	
	try {
		account = accountService.validate(aid);
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	
	card.setBalance(account.getBalance());
	card.setExpiryDate(LocalDate.now().plusYears(5));
	card.setCardType(cardDet.getCardType());
	card.setStatus("Approved");
	card.setAccount(account);

	card = cardService.insert(card);
	return ResponseEntity.ok(account);	
	
	
	
		
	}
	
	
	@GetMapping("/customer/account/card/detail/{aid}")
	public ResponseEntity<?> getCardDetails(@PathVariable int aid,ResponseMessageDto dto) {
		
		try {
			accountService.validate(aid);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		
		List<Card> list = cardService.getCardDetails(aid);
		return ResponseEntity.ok(list);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	

}



























