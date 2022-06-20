package com.inoptra.employeedepartmentdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Author: Shrikrishna Prabhumirashi
* @Description:
* Supports Account keeping for Employees working under department
* Below are few operations supported by this controller
*  - Get total salary to be paid to a department
*  - Get total salary to be paid to all departments
*  - Get average salary to be paid to a department
*  - Get average salary to be paid to all departments
**/

@RequestMapping("/account/accountstats")
public class AccountStatisticsController {
	@GetMapping("/all/total")
	public double getTotalSalaryForAllDepartments() {
		return 0.0;
	}
	
	@GetMapping("/{deptId}/total")
	public double getTotalSalaryForDepartment() {
		return 0.0;
	}
	
	@GetMapping("/all/avg")
	public double getAverageSalaryForAllDepartments() {
		return 0.0;
	}
	
	@GetMapping("/{deptId}/avg")
	public double getAverageSalaryForDepartment() {
		return 0.0;	
	}
}
