package com.springboot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.banking_system.model.IdProof;

import jakarta.transaction.Transactional;

public interface IdProofRepository extends JpaRepository<IdProof, Integer>{

	@Modifying
	@Transactional
    @Query("DELETE FROM IdProof ei WHERE ei.employee.id =?1")
	void deletebyEmployeeid(int id);

	
}
