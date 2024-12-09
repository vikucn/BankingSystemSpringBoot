package com.springboot.banking_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.banking_system.enums.BondType;
import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.enums.InvestmentType;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Bonds;
import com.springboot.banking_system.model.FixedDeposit;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.model.MutualFunds;
import com.springboot.banking_system.repository.BondsRepository;
import com.springboot.banking_system.repository.FixedDepositRepository;
import com.springboot.banking_system.repository.InvestmentRepository;
import com.springboot.banking_system.repository.MutualFundsRepository;
import com.springboot.banking_system.service.InvestmentService;

@SpringBootTest
	public class InvestmentServiceTest {

	    @InjectMocks
	    private InvestmentService investmentService;

	    @Mock
	    private InvestmentRepository investmentRepository;

	    @Mock
	    private BondsRepository bondsRepository;

	    @Mock
	    private MutualFundsRepository mutualFundsRepository;

	    @Mock
	    private FixedDepositRepository fixedDepositRepository;

	    private Investment investment;
	    private Bonds bond;
	    private MutualFunds mutualFund;
	    private FixedDeposit fixedDeposit;

	    @BeforeEach
	    public void initSetup() {
	        investment = new Investment();
	        investment.setId(1);
	        investment.setInvestmentType(InvestmentType.BONDS);
	        investment.setPurchaseDate(LocalDate.now());
	        investment.setInvestmentStatus(InvestmentStatus.APPROVED);

	        bond = new Bonds();
	        bond.setId(1);
	        bond.setFaceValue(1000);
	        bond.setInterestRate(5.0);
	        bond.setBondType(BondType.GOLD);
	        bond.setMaturityDate(LocalDate.now().plusYears(5));
	        bond.setInvestment(investment);

	        mutualFund = new MutualFunds();
	        mutualFund.setId(1);
	        mutualFund.setName("Vanguard Fund");
	        mutualFund.setUnitsPurchased(100);
	        mutualFund.setPurchasePrice(50.0);
	        mutualFund.setAmountMutualFunds(5000);
	        mutualFund.setInvestment(investment);

	        fixedDeposit = new FixedDeposit();
	        fixedDeposit.setId(1);
	        fixedDeposit.setDepositAmount(5000);
	        fixedDeposit.setInterestRateFd(5.0);
	        fixedDeposit.setMaturityDateFd(LocalDate.now().plusYears(3));
	        fixedDeposit.setAmountFd(5500);
	        fixedDeposit.setInvestment(investment);
	    }

	    @Test
	    public void validateInvestmentTestSuccess() throws ResourceNotFoundException {

	        when(investmentRepository.findById(1)).thenReturn(Optional.of(investment));

	
	        Investment validatedInvestment = investmentService.validate(1);

	     
	        assertNotNull(validatedInvestment);
	        assertEquals(investment.getId(), validatedInvestment.getId());
	        verify(investmentRepository, times(1)).findById(1);
	    }

	    @Test
	    public void validateInvestmentTestFailure() {
	    	
	   
	        when(investmentRepository.findById(999)).thenReturn(Optional.empty());

	       
	        
	        Exception exception = assertThrows(ResourceNotFoundException.class, 
	            () -> investmentService.validate(999));
	        assertEquals("Investment Not Found", exception.getMessage());
	        verify(investmentRepository, times(1)).findById(999);
	    }

	    @Test
	    public void showAllBondsTest() throws ResourceNotFoundException {
	     
	        when(bondsRepository.findAll()).thenReturn(List.of(bond));

	       
	        List<Bonds> result = investmentService.showAllBonds();

	
	        assertNotNull(result);
	        assertEquals(1, result.size());
	        assertEquals(bond.getId(), result.get(0).getId());
	        verify(bondsRepository, times(1)).findAll();
	    }

	    @Test
	    public void showAllBondsTestEmpty() {
	
	        when(bondsRepository.findAll()).thenReturn(List.of());

	      
	        Exception exception = assertThrows(ResourceNotFoundException.class, 
	            () -> investmentService.showAllBonds());
	        assertEquals("No bonds found.", exception.getMessage());
	        verify(bondsRepository, times(1)).findAll();
	    }

	    @Test
	    public void showAllMutualFundsTest() throws ResourceNotFoundException {
	   
	        when(mutualFundsRepository.findAll()).thenReturn(List.of(mutualFund));

	        
	        List<MutualFunds> result = investmentService.showAllMutualFunds();

	
	        assertNotNull(result);
	        assertEquals(1, result.size());
	        assertEquals(mutualFund.getId(), result.get(0).getId());
	        verify(mutualFundsRepository, times(1)).findAll();
	    }

	    @Test
	    public void showAllMutualFundsTestEmpty() {

	        when(mutualFundsRepository.findAll()).thenReturn(List.of());

	    
	        Exception exception = assertThrows(ResourceNotFoundException.class, 
	            () -> investmentService.showAllMutualFunds());
	        assertEquals("No mutual funds found.", exception.getMessage());
	        verify(mutualFundsRepository, times(1)).findAll();
	    }

	    @Test
	    public void showAllFixedDepositsTest() throws ResourceNotFoundException {
	   
	        when(fixedDepositRepository.findAll()).thenReturn(List.of(fixedDeposit));

	   
	        List<FixedDeposit> result = investmentService.showAllFixedDeposit();


	        assertNotNull(result);
	        assertEquals(1, result.size());
	        assertEquals(fixedDeposit.getId(), result.get(0).getId());
	        verify(fixedDepositRepository, times(1)).findAll();
	    }

	    @Test
	    public void showAllFixedDepositsTestEmpty() {
	    
	        when(fixedDepositRepository.findAll()).thenReturn(List.of());

	
	        Exception exception = assertThrows(ResourceNotFoundException.class, 
	            () -> investmentService.showAllFixedDeposit());
	        assertEquals("No fixed deposits found.", exception.getMessage());
	        verify(fixedDepositRepository, times(1)).findAll();
	    }

	    @Test
	    public void addBondsTest() {
	 
	        when(bondsRepository.save(bond)).thenReturn(bond);

	 
	        investmentService.addBonds(bond);

	
	        verify(bondsRepository, times(1)).save(bond);
	    }

	    @Test
	    public void addMutualFundsTest() {
	     
	        when(mutualFundsRepository.save(mutualFund)).thenReturn(mutualFund);

	    
	        investmentService.addMutualFunds(mutualFund);

	   
	        verify(mutualFundsRepository, times(1)).save(mutualFund);
	    }

	    @Test
	    public void addFixedDepositTest() {
	      
	        when(fixedDepositRepository.save(fixedDeposit)).thenReturn(fixedDeposit);

	
	        investmentService.addFixedDeposit(fixedDeposit);

	     
	        verify(fixedDepositRepository, times(1)).save(fixedDeposit);
	    }
	}

	

