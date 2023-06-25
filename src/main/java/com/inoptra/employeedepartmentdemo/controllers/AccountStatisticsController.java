package com.inoptra.employeedepartmentdemo.controllers;

import com.inoptra.employeedepartmentdemo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/account/accountstats")
public class AccountStatisticsController {

	@Autowired
	DepartmentService departmentService;


	@GetMapping("/all/total")
	public double getTotalSalaryForAllDepartments() {
		return departmentService.getAllTotalSalary();
	}
	
	@GetMapping("/{deptId}/total")
	public double getTotalSalaryForDepartment(@PathVariable("deptId") Integer deptId) {
		return departmentService.getTotalSalary(deptId);
	}
	
	@GetMapping("/all/avg")
	public double getAverageSalaryForAllDepartments() {

		return departmentService.getAllAvgSalary();
	}
	
	@GetMapping("/{deptId}/avg")
	public double getAverageSalaryForDepartment(@PathVariable("deptId") Integer deptId) {
		return departmentService.getAverageSalary(deptId);
	}
}
