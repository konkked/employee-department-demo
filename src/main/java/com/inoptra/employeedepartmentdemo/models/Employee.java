package com.inoptra.employeedepartmentdemo.models;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Represents Employee entity
 **/
public class Employee {

    private Long id;

    private String name;

    private Salary salary;

    private Department department;

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

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
    
    
}
