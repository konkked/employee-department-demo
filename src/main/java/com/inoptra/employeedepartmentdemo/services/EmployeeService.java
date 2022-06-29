package com.inoptra.employeedepartmentdemo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.inoptra.employeedepartmentdemo.models.Employee;
import com.inoptra.employeedepartmentdemo.repositories.EmployeeRepository;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Service layer contract which supports operations on Employee object
 **/
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public double getSalary(long empId) {
		
		Optional<Employee> optEmployee = employeeRepository.findById(empId);
		
		if(optEmployee.isEmpty()) return 0.0;
		
		Employee emp = optEmployee.get();
		
		return emp.getSalary()
							.getSalaryComonents()
							.parallelStream()
							.reduce(
									Double.valueOf(0.0), 
									(total, sc) -> total + (sc.getFactor() *  emp.getSalary().getBaseSalary()),
									(c1, c2) -> c1 + c2
							);
	}
}
