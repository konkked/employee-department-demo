package com.inoptra.employeedepartmentdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inoptra.employeedepartmentdemo.controllers.EmployeeController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Represents Employee entity
 **/
@Entity
public class Employee {

	public Employee(){

	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = new Salary();
		this.salary.setBaseSalary(salary);
		List<SalaryComponent> list = new ArrayList<>();
		list.add(new SalaryComponent("Base", 1.0));
		this.salary.setSalaryComponents(list);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToOne
	private Salary salary;

	@ManyToOne
	@JoinColumn(name = "department_id")
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
