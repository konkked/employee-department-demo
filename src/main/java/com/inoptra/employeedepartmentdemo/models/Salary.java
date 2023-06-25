package com.inoptra.employeedepartmentdemo.models;

import javax.persistence.*;
import java.util.List;

/* @Author: Shrikrishna Prabhumirashi
 * @Description:
 * SalaryComponent is dependent upon base salary and can be calculated as baseSalary multiplied by respective factor.
 *  i.e. SalaryComponent_amount = baseSalary * factor;
 *  Actual salary can be calculated as sum of all SalaryComponent amounts.
 * */
@Entity
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double baseSalary;

	@ManyToMany
	private List<SalaryComponent> salaryComponents;

	public Long getId(){ return this.id;}

	public void setId(Long id){ this.id = id; }

	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	public List<SalaryComponent> getSalaryComponents() {
		return salaryComponents;
	}
	public void setSalaryComponents(List<SalaryComponent> salaryComponents) {
		this.salaryComponents = salaryComponents;
	}
	
}
