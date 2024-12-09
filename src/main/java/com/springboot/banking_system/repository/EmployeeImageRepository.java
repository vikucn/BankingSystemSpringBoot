package com.springboot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.banking_system.model.EmployeeImage;

import jakarta.transaction.Transactional;

public interface EmployeeImageRepository extends JpaRepository<EmployeeImage, Integer>{

	
	@Modifying
	@Transactional
    @Query("DELETE FROM EmployeeImage ei WHERE ei.employee.id =?1")
	void deletebyEmployeeid(int id);

	



}
