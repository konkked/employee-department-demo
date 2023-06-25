package com.inoptra.employeedepartmentdemo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* @Author: Shrikrishna Prabhumirashi
 * @Description:
 * SalaryComponent is dependent upon base salary and can be calculated as baseSalary multiplied by respective factor.
 *  i.e. SalaryComponent_amount = baseSalary * factor;
 *  Actual salary can be calculated as sum of all SalaryComponent amounts.
 * */
@Entity
public class SalaryComponent {

	public SalaryComponent(){
	}

	public SalaryComponent(String name, double factor){
		this.name = name;
		this.factor = factor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double factor;

	public Long getId(){ return this.id; }

	public void setId(Long id){ this.id = id; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
}
