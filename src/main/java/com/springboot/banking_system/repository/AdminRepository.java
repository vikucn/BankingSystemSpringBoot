package com.springboot.banking_system.repository;

import com.springboot.banking_system.model.Admin;
import com.springboot.banking_system.model.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a join a.user u where u.username=?1")
	Admin getAdminDetailsByUsername(String username);

}
