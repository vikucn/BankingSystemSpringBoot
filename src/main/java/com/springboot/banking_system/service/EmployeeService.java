package com.springboot.banking_system.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.banking_system.dto.EmployeeUpdateDto;
import com.springboot.banking_system.enums.Role;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Employee;
import com.springboot.banking_system.model.EmployeeImage;
import com.springboot.banking_system.model.IdProof;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.repository.EmployeeImageRepository;
import com.springboot.banking_system.repository.EmployeeRepository;
import com.springboot.banking_system.repository.IdProofRepository;
import com.springboot.banking_system.repository.UserRepository;



@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder getEncoder;
	@Autowired
	private EmployeeImageRepository employeeImageRepository;
	
	@Autowired
	private IdProofRepository idProofRepository;

	public Employee insert(Employee employee){
		
			return employeeRepository.save(employee);
			
		}

	public Employee validate(int eid) throws ResourceNotFoundException {
		
		Optional<Employee>optional = employeeRepository.findById(eid);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Given id is invalid try again...");
		}
		
		Employee employee = optional.get();
		
		
		return employee;
		
		
	}

	public void delete(int id) {
		employeeImageRepository.deletebyEmployeeid(id);
		idProofRepository.deletebyEmployeeid(id);
		employeeRepository.deleteById(id);
		
		
	}

	public List<Employee> getEmployeeDetail(int id) {
		return employeeRepository.getEmployeeDetail(id);
	}

	public List<Employee> showAllEmployees() {
		return employeeRepository.findAll();
	}

	
	public List<EmployeeImage>getAllEmployeeImages(){
		return employeeImageRepository.findAll();
	}
//	public Employee addEmployee(Employee employee) {
//		/*detach user from given executive */
//		User user = employee.getUser();
//		user.setRole(Role.EMPLOYEE);
//		
//		String plainTextPass = getEncoder.encode(user.getPassword());
//		String encPassword = getEncoder.encode(plainTextPass);
//		
//		user = userRepository.save(user); //complete user with role, password and id
//		
//		employee.setUser(user);
//		
//		return employeeRepository.save(employee);
//	}
	public Employee addEmployee(Employee employee) {
		/*detach user from given executive */
		User user = employee.getUser();
		user.setRole(Role.EMPLOYEE);
		
		String encPassword = getEncoder.encode(user.getPassword());
		 
		user.setPassword(encPassword);
		user = userRepository.save(user); //complete user with role, password and id
		
		employee.setUser(user);
		
		return employeeRepository.save(employee);
	}
	public EmployeeImage uploadImage(int eid, MultipartFile file) throws IOException, ResourceNotFoundException {
		System.out.println(file.getOriginalFilename());
		String location = "/Users/chauhanji/Documents/AdminBank/adminAngular/public/images";
		Path path = Path.of(location, file.getOriginalFilename()); 
		//System.out.println(path.toString());
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw e; 
		}
		
		Employee employee=null;
		try {
			employee = getById(eid);
		} catch (ResourceNotFoundException e) {
			 throw e; 
		}
		
		EmployeeImage ei = new EmployeeImage();
		ei.setFileName(file.getOriginalFilename());
		ei.setPath(path.toString());
		ei.setEmployee(employee);
		
		return addEmployeeImage(ei);
	}

	private EmployeeImage addEmployeeImage(EmployeeImage ei) {
		return employeeImageRepository.save(ei);
	}

	public Employee getById(int eid) throws ResourceNotFoundException {
		Optional<Employee> optional =   employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invalid Employee id");
		
		return optional.get();
	}

public Employee findEmployeeById(int id) throws ResourceNotFoundException {
		Optional<Employee> optional =employeeRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Employee id invalid");
		
		Employee employee = optional.get();
		return employee;
	}


public Employee updateEmployeeDetails(Employee employee, EmployeeUpdateDto newEmployee) {
	
	if(newEmployee.getFirstName()!=null)
		employee.setFirstName(newEmployee.getFirstName());
	if(newEmployee.getLastName()!=null)
		employee.setLastName(newEmployee.getLastName());
	if(newEmployee.getDateOfBirth()!=null)
		employee.setDateOfBirth(newEmployee.getDateOfBirth());
	if(newEmployee.getContactNumber()!=null)
		employee.setContactNumber(newEmployee.getContactNumber());
	if(newEmployee.getAddress()!=null)
		employee.setAddress(newEmployee.getAddress());
	if(newEmployee.getGender()!=null)
		employee.setGender(newEmployee.getGender());
	
	return employeeRepository.save(employee);
	
}

public EmployeeImage uploadProfilePic(int eid, MultipartFile file) throws IOException, ResourceNotFoundException {
	System.out.println(file.getOriginalFilename());
	String location = "/Users/chauhanji/Documents/AdminBank/adminAngular/public/images/ProfilePicture";
	Path path = Path.of(location, file.getOriginalFilename()); 
	//System.out.println(path.toString());
	try {
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
		throw e; 
	}
	
	Employee employee=null;
	try {
		employee = getById(eid);
	} catch (ResourceNotFoundException e) {
		 throw e; 
	}
	
	EmployeeImage ei = new EmployeeImage();
	ei.setFileName(file.getOriginalFilename());
	ei.setPath(path.toString());
	ei.setEmployee(employee);
	
	return employeeImageRepository.save(ei);
}

public IdProof uploadIdProof(int eid, MultipartFile file) throws IOException, ResourceNotFoundException {
	System.out.println(file.getOriginalFilename());
	String location = "/Users/chauhanji/Documents/AdminBank/adminAngular/public/images/IdProof";
	Path path = Path.of(location, file.getOriginalFilename()); 
	//System.out.println(path.toString());
	try {
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
		throw e; 
	}
	
	Employee employee=null;
	try {
		employee = getById(eid);
	} catch (ResourceNotFoundException e) {
		 throw e; 
	}
	
	IdProof ei = new IdProof();
	ei.setFileName(file.getOriginalFilename());
	ei.setPath(path.toString());
	ei.setEmployee(employee);
	
	return idProofRepository.save(ei);
}

	
}
