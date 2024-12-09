package com.springboot.banking_system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.dto.TransactionStatDto;
import com.springboot.banking_system.dto.UserCountStatDto;
import com.springboot.banking_system.enums.Role;
import com.springboot.banking_system.enums.TransactionType;
import com.springboot.banking_system.model.Account;
import com.springboot.banking_system.model.Transaction;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.repository.AccountRepository;
import com.springboot.banking_system.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionStatDto transactionStatDto;

	public Transaction depositMoney(int aid, double amount) {
		
		Account account = accountRepository.findById(aid).get();
		accountRepository.depositMoney(aid, amount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(account.getAccountNumber());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setAmount(amount);
		transaction.setAccount(account);
		
		return transactionRepository.save(transaction);
		
		
		
		
	}

	public Transaction withdrawMoney(int aid, double amount) {

		Account account = accountRepository.findById(aid).get();
		accountRepository.withdrawMoney(aid, amount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(account.getAccountNumber());
		transaction.setTransactionType(TransactionType.WITHDRAW);
		transaction.setAmount(amount);
		transaction.setAccount(account);
		
		return transactionRepository.save(transaction);
		
	}

	public Transaction transferMoney(int aid, String reaccno, double amount) {
		Account account = accountRepository.findById(aid).get();
		String senderAccount = account.getAccountNumber();
		String receiverAccount = reaccno;
		
		accountRepository.withdraw(senderAccount, amount);
		accountRepository.deposit(receiverAccount, amount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(senderAccount);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setAmount(amount);
		transaction.setAccount(account);
		
		return transactionRepository.save(transaction);
	}

	public List<Transaction> showAllTransaction() {
		List<Transaction> list = transactionRepository.findAll();
		return list;
	}

	public List<Transaction> showWithdraw() {
		List<Transaction> list = transactionRepository.findByTransactionType(TransactionType.WITHDRAW);
		return list;
	}
	
	public TransactionStatDto getTransactionStats() {
		List<Transaction> list = transactionRepository.findAll();
		//list.stream().forEach(System.out :: println);
		 Map <TransactionType, List<Transaction>> map = list
				 					.parallelStream()
				 					.collect(Collectors.groupingBy(e->e.getTransactionType()));
		// System.out.println(map);
		 Set<TransactionType> listRoles = map.keySet();
		 List<Integer> listData = new ArrayList<>();
		 for(TransactionType TransactionType : map.keySet()) {
			 int num =  map.get(TransactionType).size();
			 listData.add(num);
		 }
		transactionStatDto.setLabels(listRoles);
		transactionStatDto.setData(listData);
		 return transactionStatDto;
		 
	}

	public List<Transaction> filterByDate(LocalDate startDate, LocalDate endDate) {
		return transactionRepository.filterByDate(startDate,endDate);
	}

//	public List<Transaction> showWithdraw() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
	

}
