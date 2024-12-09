package com.springboot.banking_system.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.banking_system.dto.AccountDto;
import com.springboot.banking_system.dto.CustomerDto;
import com.springboot.banking_system.dto.EmployeeDto;
import com.springboot.banking_system.dto.EmployeeUpdateDto;
import com.springboot.banking_system.dto.InvestmentDtoBonds;
import com.springboot.banking_system.dto.InvestmentDtoFixedDeposit;
import com.springboot.banking_system.dto.InvestmentDtoMutualFunds;
import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.dto.TransactionStatDto;
import com.springboot.banking_system.dto.UserCountStatDto;
import com.springboot.banking_system.enums.InvestmentStatus;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Bonds;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.model.EmployeeImage;
import com.springboot.banking_system.model.FixedDeposit;
import com.springboot.banking_system.model.IdProof;
import com.springboot.banking_system.model.Investment;
import com.springboot.banking_system.model.Loan;
import com.springboot.banking_system.model.MutualFunds;
import com.springboot.banking_system.model.Transaction;
import com.springboot.banking_system.service.AdminService;
import com.springboot.banking_system.service.CustomerService;
import com.springboot.banking_system.service.EmployeeService;
import com.springboot.banking_system.service.InvestmentService;
import com.springboot.banking_system.service.LoanService;
import com.springboot.banking_system.service.TransactionService;
import com.springboot.banking_system.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private InvestmentService investmentService;
	@Autowired
	private UserService userService;
	
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@PostMapping("/admin/register")
	public Admin registerAdmin(@RequestBody Admin admin,ResponseMessageDto dto) {
		return adminService.insert(admin);
	}

	
	@GetMapping("/api/users-stat")
	public UserCountStatDto getUserStats() {
		return userService.getUserStats();
	}
	
	@GetMapping("admin/api/transaction-stat")
	public TransactionStatDto getTransactionStats() {
		return transactionService.getTransactionStats();
	}
	
	
	@PostMapping("/admin/add")
	public void addAdmin(@RequestBody Admin admin) {
		adminService.insert(admin);
	}
	
	@GetMapping("/admin/all")
	public List<Admin> getAllAdmin() {
		List<Admin> list = adminService.getAllAdmin();
		return list;
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable int id, ResponseMessageDto dto) {
		//validate id
		try {
			employeeService.validate(id);
			employeeService.delete(id);
		} catch (ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		dto.setMsg("employee deleted");
		return ResponseEntity.ok(dto);
		
	}
	
	@PostMapping("/admin/batch/add")
	public List <Admin> batchInsert(@RequestBody List<Admin> list) {
		return adminService.insertInBatch(list);
	}
	
	
	@PutMapping("/admin/update/{id}")
	public ResponseEntity<?> updateAdmin(@PathVariable int id,@RequestBody Admin newAdmin, ResponseMessageDto dto) {
	
	try {
		Admin existingAdminDb= adminService.validate(id);
		if(newAdmin.getFirstName()!=null)
			existingAdminDb.setFirstName(newAdmin.getFirstName());
		if(newAdmin.getMiddleName()!=null)
			existingAdminDb.setMiddleName(newAdmin.getMiddleName());
		if(newAdmin.getLastName()!=null)
			existingAdminDb.setLastName(newAdmin.getLastName());
		
		//re enter this existing admin having new updated value
		existingAdminDb = adminService.insert(existingAdminDb);
		return ResponseEntity.ok(existingAdminDb);
		
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	}
	
	@GetMapping("admin/cutomers/all")
	public List<Customer> getAllCustomers(){
		List<Customer> list = customerService.showAllCustomers();
		return list;
	}
	
	@GetMapping("admin/employee/all")
	public List<Employee> getAllEmployees(){
		List<Employee> list = employeeService.showAllEmployees();
		return list;
	}
	
	@GetMapping("admin/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) throws ResourceNotFoundException {
	
			 return employeeService.validate(id);
	
	}
	
	@GetMapping("admin/alltransaction")
	public List<Transaction> getTransactions() {
		List<Transaction> list = transactionService.showAllTransaction();
		return list;
	}
	
	@GetMapping("admin/withdrawTrasaction")
	public List<Transaction> showWithdraw(){
	List<Transaction> list = transactionService.showWithdraw();
	return list;
	}
	
	@GetMapping("admin/showLoans")
	public List<Loan> showLoans()
	{
		List<Loan> list = loanService.showAll();
		return list;
	}
	
	@PutMapping("admin/updateLoan/{newInterestRate}")
	public void updateLoanInterestRate(@PathVariable double newInterestRate) {
		loanService.insertNewRate(newInterestRate);
		
	}
	
	@GetMapping("admin/showinvestments")
	public List<Investment> showAllInvestments(){
		List<Investment> list = investmentService.showAllInvestments();
		return list;
	}
	
	@PostMapping("admin/employee/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@PostMapping("/admin/employee/idProof/upload/{eid}")
	public IdProof uploadIdProof(@PathVariable int eid, @RequestParam MultipartFile file) 
	throws IOException, ResourceNotFoundException{
		
		return employeeService.uploadIdProof(eid,file);
		
		
	}
	@PostMapping("/admin/employee/profilePic/upload/{eid}")
	public EmployeeImage uploadProfilePic(@PathVariable int eid, @RequestParam MultipartFile file) 
	throws IOException, ResourceNotFoundException{
		
		return employeeService.uploadProfilePic(eid,file);
		
		
	}
	
	@GetMapping("/api/employee/all")
	public List<EmployeeDto> getAllProducts() {
		List<Employee> pList =  employeeService.showAllEmployees();
		List<EmployeeImage> imageList= employeeService.getAllEmployeeImages();
		
		List<EmployeeDto> listDto = new ArrayList<>();
		for(Employee p : pList) {
			EmployeeDto dto = new EmployeeDto();
			dto.setId(p.getId());
			dto.setFirstName(p.getFirstName());
			dto.setLastName(p.getLastName());
			dto.setDateOfBirth(p.getDateOfBirth());
			dto.setGender(p.getLastName());
			dto.setContactNumber(p.getContactNumber());
			dto.setAddress(p.getAddress());
			dto.setSalary(p.getSalary());
		
			
			List<EmployeeImage> iList =
					imageList.stream()
						.filter(i->i.getEmployee().getId() == p.getId())
						.toList();
			dto.setImages(iList);
			listDto.add(dto);
		}
		
		return listDto;
	}
	@GetMapping("/employee/one/{id}")
	public EmployeeDto getById(@PathVariable int id) throws ResourceNotFoundException {
		 Employee p = employeeService.getById(id);
			List<EmployeeImage> imageList= employeeService.getAllEmployeeImages();
		 EmployeeDto dto = new EmployeeDto();
			dto.setId(p.getId());
			dto.setFirstName(p.getFirstName());
			dto.setLastName(p.getLastName());
			dto.setDateOfBirth(p.getDateOfBirth());
			dto.setGender(p.getLastName());
			dto.setContactNumber(p.getContactNumber());
			dto.setAddress(p.getAddress());
			dto.setSalary(p.getSalary());
		
			List<EmployeeImage> iList =
					imageList.stream()
						.filter(i->i.getEmployee().getId() == p.getId())
						.toList();
			dto.setImages(iList);
			 
			return dto; 
	}
	
	@PostMapping("admin/investmentUpdate/{id}")
	public ResponseEntity<ResponseMessageDto> updateInvestmentStatus(@PathVariable int id,
			@RequestParam InvestmentStatus investmentStatus,
			ResponseMessageDto dto) {
		try {
		investmentService.validate(id);
		investmentService.updateInvestmentStatus(id,investmentStatus);
		}catch(ResourceNotFoundException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
		dto.setMsg("investment updated");
		return ResponseEntity.ok(dto);
	}
	@GetMapping("admin/details")
	public Admin getAdminDetailsByUsername(@RequestParam String username) {
		Admin admin =  adminService.getAdminDetailsByUsername(username);
		return admin;
	}
	
	@PostMapping("admin/update")
	public Admin postAdmin(@RequestBody Admin admin) {
		return adminService.insert(admin);
	}
	

	
	@PostMapping("/admin/employeedto/update/{id}")
	public Employee updateEmployeeDtoDetails(@PathVariable int id, @RequestBody EmployeeUpdateDto newEmployee ) throws ResourceNotFoundException {
	Employee employee = employeeService.findEmployeeById(id);
		
		return employeeService.updateEmployeeDetails(employee,newEmployee);
	}
	


	@GetMapping("admin/getCustomerAllDetails")
	public List<CustomerDto> getCustomerAllDetails() {
		return customerService.getCustomerAllDetails();
	}
	
	
	@GetMapping("/api/CustomerDetails/all")
	public Page<CustomerDto> getCustomerDetails(
					@RequestParam(required = false, defaultValue = "0") String page, 
					@RequestParam(required = false, defaultValue = "1000000") String size) 
							throws Exception{
		Pageable pageable = null; 
		
		try {
			pageable =   PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		}
		catch(Exception e) {
			throw e; 
		}
		
		Page<CustomerDto> list =  customerService.getCustomerDetails(pageable);
		return list; 
	}
	
	
	@GetMapping("admin/getCustomerDetailsByAccNum")
	public List<CustomerDto> getCustomerDetailsByAccNum(@RequestParam String accountNumber) throws ResourceNotFoundException
	{
		return customerService.getCustomerDetailsByAccNum(accountNumber);
	}
	
	@GetMapping("admin/transaction/date")
	public List<Transaction> filterByDate(@RequestParam 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,@RequestParam 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate) {
		return  transactionService.filterByDate(startDate,endDate);
	}
	
	@GetMapping("admin/accountDto")
	public List<AccountDto> getAccountDtoDetails(@RequestParam String accountNumber) throws ResourceNotFoundException{
		return customerService.getAccountDtoDetails(accountNumber);
	}
	
	//Show investment details
	
	@GetMapping("admin/bonds/details")
	public List<Bonds> showAllBonds() throws ResourceNotFoundException{
		logger.info("Showing All bonds");
		return investmentService.showAllBonds();
	}

	@GetMapping("admin/mutualFunds/details")
	public List<MutualFunds> showAllMutualFunds() throws ResourceNotFoundException{
		return investmentService.showAllMutualFunds();
	}

	@GetMapping("admin/fixedDeposit/details")
	public List<FixedDeposit> showAllFixedDeposit() throws ResourceNotFoundException{
		return investmentService.showAllFixedDeposit();
	}
	
	//Show Customers in Investments
	
	@GetMapping("admin/investment/bond/customer/{id}")
	public List<InvestmentDtoBonds> showCustomersInBond(@PathVariable int id) throws ResourceNotFoundException{
		return customerService.showCustomersInBond(id);
	}
	@GetMapping("admin/investment/mutualFunds/customer/{id}")
	public List<InvestmentDtoMutualFunds> showCustomersInMutualFunds(@PathVariable int id) throws ResourceNotFoundException{
		return customerService.showCustomersInMutualFunds(id);
	}

	@GetMapping("admin/investment/fixedDeposit/customer/{id}")
	public List<InvestmentDtoFixedDeposit> showCustomersInFixedDeposit(@PathVariable int id) throws ResourceNotFoundException{
		return customerService.showCustomersInFixedDeposit(id);
	}
	
	
	//add Investments
	
	
	@PostMapping("admin/investment/addBonds")
	public void addBonds(@RequestBody Bonds bond) {
		 investmentService.addBonds(bond);
	}
	
	@PostMapping("admin/investment/addMutualFunds")
	public void addMutualFunds(@RequestBody MutualFunds mutualFund) {
		 investmentService.addMutualFunds(mutualFund);
	}
	@PostMapping("admin/investment/addFixedDeposit")
	public void addFixedDeposit(@RequestBody FixedDeposit fixedDeposit) {
		 investmentService.addFixedDeposit(fixedDeposit);
	}
	
	

	
	
	

	
}
