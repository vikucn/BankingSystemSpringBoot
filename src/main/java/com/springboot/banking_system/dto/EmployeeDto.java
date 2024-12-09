package com.springboot.banking_system.dto;

import java.time.LocalDate;
import java.util.List;

import com.springboot.banking_system.model.EmployeeImage;

import jakarta.persistence.Column;

public class EmployeeDto {

	
	private int id;
	private String firstName;
	private String lastName;

	private LocalDate dateOfBirth;

	private String gender;
	

	private String contactNumber;

	private String address;
	
	private double salary;
	
	List<EmployeeImage> images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<EmployeeImage> getImages() {
		return images;
	}

	public void setImages(List<EmployeeImage> images) {
		this.images = images;
	}
	
	
	
}
