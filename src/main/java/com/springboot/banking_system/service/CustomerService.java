package com.springboot.banking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.dto.AccountDto;
import com.springboot.banking_system.dto.CustomerDto;
import com.springboot.banking_system.dto.InvestmentDto;
import com.springboot.banking_system.dto.InvestmentDtoBonds;
import com.springboot.banking_system.dto.InvestmentDtoFixedDeposit;
import com.springboot.banking_system.dto.InvestmentDtoMutualFunds;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer insert(Customer customer){
		
			return customerRepository.save(customer);
			
		}
		

	public Customer validate(int cid) throws ResourceNotFoundException {
		
		Optional<Customer>optional = customerRepository.findById(cid);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		
		Customer customer = optional.get();
		
		return customer;
		
		
	}

	public void delete(int id) {
		customerRepository.deleteById(id);
		
	}

	public List<Customer> getCustomerDetail(int id) {
		return customerRepository.getCustomerDetail(id);
	}

	public List<Customer>showAllCustomers(){
		return customerRepository.findAll();
		}


//	public ResponseEntity<?> getCustomerAllDetails() throws ResourceNotFoundException {
//		Optional<CustomerDto>optional =
//		
//	}
	public List<CustomerDto> getCustomerAllDetails() {
		return customerRepository.getCustomerAllDetails();
	}
	
	
	public Page<CustomerDto> getCustomerDetails(Pageable pageable) {
		
		return customerRepository.getCustomerDetails(pageable);
	}


//	public List<CustomerDto> getCustomerDetailsByAccNum(String accNumber) {
//		return customerRepository.getCustomerDetailsByAccNum(accNumber);
//	}
//
//
//	public List<AccountDto> getAccountDtoDetails(String accountNumber) {
//		return customerRepository.getAccountDtoDetails(accountNumber);
//	}
	
	 public List<CustomerDto> getCustomerDetailsByAccNum(String accNumber) throws ResourceNotFoundException {
	        List<CustomerDto> customers = customerRepository.getCustomerDetailsByAccNum(accNumber);
	        if (customers.isEmpty()) {
	            throw new ResourceNotFoundException("No customer found with account number: " + accNumber);
	        }
	        return customers;
	    }

	    public List<AccountDto> getAccountDtoDetails(String accountNumber) throws ResourceNotFoundException {
	        List<AccountDto> accounts = customerRepository.getAccountDtoDetails(accountNumber);
	        if (accounts.isEmpty()) {
	            throw new ResourceNotFoundException("No account details found for account number: " + accountNumber);
	        }
	        return accounts;
	    }

	
	
//	public List<InvestmentDtoBonds> showCustomersInBond(int id) throws ResourceNotFoundException {
//		
//		Optional<InvestmentDtoBonds> optional = customerRepository.showCustomersInBond(id)
//		
//	}
//	
//	public Customer validate(int cid) throws ResourceNotFoundException {
//		
//		Optional<Customer>optional = customerRepository.findById(cid);
//		
//		if(optional.isEmpty()) {
//			throw new ResourceNotFoundException("Given id is invalid try again...");
//		}
//		
//		Customer customer = optional.get();
//		
//		return customer;
//		
//		
//	}

//	public List<InvestmentDtoBonds> showCustomersInBond(int id) {
//		return customerRepository.showCustomersInBond(id);
//	}
//
//	public List<InvestmentDtoMutualFunds> showCustomersInMutualFunds(int id) {
//		return customerRepository.showCustomersInMutualFunds(id);
//	}
//
//
//	public List<InvestmentDtoFixedDeposit> showCustomersInFixedDeposit(int id) {
//		return customerRepository.showCustomersInFixedDeposit(id);
//	}
	

	    public List<InvestmentDtoBonds> showCustomersInBond(int id) throws ResourceNotFoundException {
	        List<InvestmentDtoBonds> customers = customerRepository.showCustomersInBond(id);
	        if (customers.isEmpty()) {
	            throw new ResourceNotFoundException("No customers found for bond ID: " + id);
	        }
	        return customers;
	    }

	    public List<InvestmentDtoMutualFunds> showCustomersInMutualFunds(int id) throws ResourceNotFoundException {
	        List<InvestmentDtoMutualFunds> customers = customerRepository.showCustomersInMutualFunds(id);
	        if (customers.isEmpty()) {
	            throw new ResourceNotFoundException("No customers found for mutual fund ID: " + id);
	        }
	        return customers;
	    }

	    public List<InvestmentDtoFixedDeposit> showCustomersInFixedDeposit(int id) throws ResourceNotFoundException {
	        List<InvestmentDtoFixedDeposit> customers = customerRepository.showCustomersInFixedDeposit(id);
	        if (customers.isEmpty()) {
	            throw new ResourceNotFoundException("No customers found for fixed deposit ID: " + id);
	        }
	        return customers;
	    }
	





	

}
