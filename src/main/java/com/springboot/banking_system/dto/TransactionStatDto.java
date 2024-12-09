package com.springboot.banking_system.dto;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.springboot.banking_system.enums.Role;
import com.springboot.banking_system.enums.TransactionType;

@Component
public class TransactionStatDto {

	private Set<TransactionType> labels;
	private List<Integer> data;
	 
	public Set<TransactionType> getLabels() {
		return labels;
	}
	public void setLabels(Set<TransactionType> labels) {
		this.labels = labels;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
}
