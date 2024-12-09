package com.springboot.banking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.enums.Role;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Customer;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.repository.AccountRepository;
import com.springboot.banking_system.repository.AdminRepository;
import com.springboot.banking_system.repository.CustomerRepository;
import com.springboot.banking_system.repository.UserRepository;

@Service
public class AdminService {


	@Autowired
   private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	public Admin insert(Admin admin) {
//		return adminRepository.save(admin);
//	}

	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		 adminRepository.deleteById(id);
	}

	public Admin validate(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Admin> optional = adminRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Admin id invalid");
		
		Admin admin = optional.get();
		return admin;
	}

	public List<Admin> insertInBatch(List<Admin> list) {
		// TODO Auto-generated method stub
		 return adminRepository.saveAll(list);
	}

	public Admin getAdminDetailsByUsername(String username) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminDetailsByUsername(username);
	}

//	
//	public Admin insert(Admin admin) {
//		
//		
//		User user = userRepository.save(admin.getUser());
//		admin.setUser(user);
//		return adminRepository.save(admin);
//	}
	public Admin insert(Admin admin) {
		
		User user = admin.getUser();
		user.setRole(Role.ADMIN);
		String encPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encPassword);
		user = userRepository.save(user);
		
		admin.setUser(user);
		return adminRepository.save(admin);
	}
	
}
