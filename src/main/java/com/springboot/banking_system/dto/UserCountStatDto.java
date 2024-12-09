package com.springboot.banking_system.dto;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.springboot.banking_system.enums.Role;

@Component
public class UserCountStatDto {

	private Set<Role> labels;
	private List<Integer> data;
	 
	public Set<Role> getLabels() {
		return labels;
	}
	public void setLabels(Set<Role> labels) {
		this.labels = labels;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
}
