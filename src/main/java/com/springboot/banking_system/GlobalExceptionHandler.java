package com.springboot.banking_system;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.banking_system.dto.ResponseMessageDto;
import com.springboot.banking_system.exception.ResourceNotFoundException;

import com.springboot.banking_system.service.EmployeeService;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResponseMessageDto dto;
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e) {
		dto.setMsg(e.getMessage());
		logger.error("ResouceNotFoundException thrown" + dto.getMsg());
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> handleIOException(IOException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
}

