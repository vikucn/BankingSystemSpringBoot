package com.springboot.banking_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.banking_system.dto.UserCountStatDto;
import com.springboot.banking_system.enums.Role;
import com.springboot.banking_system.exception.InvalidUsernameException;
import com.springboot.banking_system.exception.ResourceNotFoundException;
import com.springboot.banking_system.model.User;
import com.springboot.banking_system.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private UserCountStatDto userCountStatDto;
	
	
	public UserCountStatDto getUserStats() {
		List<User> list = userRepository.findAll();
		//list.stream().forEach(System.out :: println);
		 Map <Role, List<User>> map = list
				 					.parallelStream()
				 					.collect(Collectors.groupingBy(e->e.getRole()));
		// System.out.println(map);
		 Set<Role> listRoles = map.keySet();
		 List<Integer> listData = new ArrayList<>();
		 for(Role role : map.keySet()) {
			 int num =  map.get(role).size();
			 listData.add(num);
		 }
		 userCountStatDto.setLabels(listRoles);
		 userCountStatDto.setData(listData);
		 return userCountStatDto;
		 
	}

	public User signUp(User user) throws InvalidUsernameException {
		//check for username duplicacy 
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
		
		//encrypt the password 
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		user.setRole(Role.ADMIN);
		
		return userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		 //i am sure that username is valid as Spring has already checked it 
		return userRepository.findByUsername(username).get();
	}

	public User updateUserStatus(int id, boolean status) throws ResourceNotFoundException {
		Optional<User> optional =  userRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("UserId Invalid");
		
		User user = optional.get();
		user.setEnabled(status);
		return userRepository.save(user);
	}
	
      public User validate(int id) throws ResourceNotFoundException {
		
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("User id is invalid");
		
		return optional.get();
		
	}

}
