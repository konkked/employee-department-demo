package com.inoptra.employeedepartmentdemo.models;

import java.util.List;

/* @Author: Shrikrishna Prabhumirashi
 * @Description:
 * SalaryComponent is dependent upon base salary and can be calculated as baseSalary multiplied by respective factor.
 *  i.e. SalaryComponent_amount = baseSalary * factor;
 *  Actual salary can be calculated as sum of all SalaryComponent amounts.
 * */
public class Salary {
	private double baseSalary;
	private List<SalaryComponent> salaryComonents;
}
