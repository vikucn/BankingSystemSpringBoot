package com.springboot.banking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Bonds;
import com.springboot.banking_system.model.FixedDeposit;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.model.MutualFunds;
import com.springboot.banking_system.repository.BondsRepository;
import com.springboot.banking_system.repository.FixedDepositRepository;
import com.springboot.banking_system.repository.InvestmentRepository;
import com.springboot.banking_system.repository.MutualFundsRepository;

@Service
public class InvestmentService {
	
	@Autowired
	private InvestmentRepository investmentRepository;
	
	@Autowired
	private BondsRepository bondsRepository;
	
	@Autowired
	private MutualFundsRepository mutualFundsRepository;
	
	@Autowired
	private FixedDepositRepository fixedDepositRepository;

	public List<Investment> showAllInvestments() {
		
		return investmentRepository.findAll();
	}

//	public void updateInvestmentStatus( int id,InvestmentStatus investmentStatus) {
//		investmentRepository.updateInvestmentStatus(id,investmentStatus);
//		
//	}

	public Investment validate(int id) throws ResourceNotFoundException{
		Optional<Investment> optional = investmentRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Investment Not Found");
		
		Investment investment = optional.get();
		return investment;
		
	}

	public void updateInvestmentStatus(int id, InvestmentStatus investmentStatus) {
		// TODO Auto-generated method stub
		investmentRepository.updateInvestmentStatus(id,investmentStatus);
		
	}
//
//	public List<Bonds> showAllBonds() {
//	
//		return bondsRepository.findAll();
//	}
//
//	public List<MutualFunds> showAllMutualFunds() {
//		return mutualFundsRepository.findAll();
//	}
//
//	public List<FixedDeposit> showAllFixedDeposit() {
//		return fixedDepositRepository.findAll();
//	}
	
	  public List<Bonds> showAllBonds() throws ResourceNotFoundException {
	        List<Bonds> bonds = bondsRepository.findAll();
	        if (bonds.isEmpty()) {
	            throw new ResourceNotFoundException("No bonds found.");
	        }
	        return bonds;
	    }

	    public List<MutualFunds> showAllMutualFunds() throws ResourceNotFoundException {
	        List<MutualFunds> mutualFunds = mutualFundsRepository.findAll();
	        if (mutualFunds.isEmpty()) {
	            throw new ResourceNotFoundException("No mutual funds found.");
	        }
	        return mutualFunds;
	    }

	    public List<FixedDeposit> showAllFixedDeposit() throws ResourceNotFoundException {
	        List<FixedDeposit> fixedDeposits = fixedDepositRepository.findAll();
	        if (fixedDeposits.isEmpty()) {
	            throw new ResourceNotFoundException("No fixed deposits found.");
	        }
	        return fixedDeposits;
	    }

	public void addBonds(Bonds bond) {
		bondsRepository.save(bond);
	}

	public void addMutualFunds(MutualFunds mutualFund) {
		mutualFundsRepository.save(mutualFund);
	}

	public void addFixedDeposit(FixedDeposit fixedDeposit) {
		fixedDepositRepository.save(fixedDeposit);
	}


	

}
