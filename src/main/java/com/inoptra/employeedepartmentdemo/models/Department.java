package com.inoptra.employeedepartmentdemo.models;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Represents {@code Department} entity
 **/
@Entity
public class Department {

	public Department() {
	}

	public Department(String name, List<Employee> employees){
		this.name = name;
		this.employees = employees;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
    
}
