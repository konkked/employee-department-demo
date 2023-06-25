package com.inoptra.employeedepartmentdemo.services;

import java.util.List;
import java.util.Optional;

import com.inoptra.employeedepartmentdemo.helpers.EmployeeHelper;
import com.inoptra.employeedepartmentdemo.models.Employee;
import com.inoptra.employeedepartmentdemo.models.Salary;
import com.inoptra.employeedepartmentdemo.models.SalaryComponent;
import com.inoptra.employeedepartmentdemo.repositories.EmployeeRepository;
import com.inoptra.employeedepartmentdemo.repositories.SalaryComponentRepository;
import com.inoptra.employeedepartmentdemo.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Service layer contract which supports operations on Employee object
 **/
@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private SalaryComponentRepository salaryComponentRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee.orElse(null);
	}

	public Employee createEmployee(Employee employee) {
		Salary salary = employee.getSalary();
		// Save the Salary object if it is not already saved
		if (salary.getId() == null) {
			List<SalaryComponent> components = salary.getSalaryComponents();
			for (SalaryComponent component:components) {
				if (component.getId() == null) {
					salaryComponentRepository.save(component);
				}
			}
			salary = salaryRepository.save(salary);
		}

		employee.setSalary(salary);
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Optional<Employee> employee = employeeRepository.findById(id);

		Salary updatedEmployeeSalary = updatedEmployee.getSalary();
		// Save the Salary object if it is not already saved
		if (updatedEmployeeSalary.getId() == null) {
			List<SalaryComponent> components = updatedEmployeeSalary.getSalaryComponents();
			for (SalaryComponent component:components) {
				if (component.getId() == null) {
					salaryComponentRepository.save(component);
				}
			}
			updatedEmployeeSalary = salaryRepository.save(updatedEmployeeSalary);
		}

		if (employee.isPresent()) {
			Employee existingEmployee = employee.get();
			existingEmployee.setName(updatedEmployee.getName());
			Salary existingSalary = existingEmployee.getSalary();
			existingEmployee.setSalary(updatedEmployeeSalary);
			if (existingSalary.getId() != null) {
				List<SalaryComponent> components = existingSalary.getSalaryComponents();
				for (SalaryComponent component:components) {
					if (component.getId() == null) {
						salaryComponentRepository.delete(component);
					}
				}
				salaryRepository.delete(existingSalary);
			}
			existingEmployee.setDepartment(updatedEmployee.getDepartment());
			return employeeRepository.save(existingEmployee);
		} else {
			return null;
		}
	}

	public boolean deleteEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			employeeRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Compute and return salary for given employee
	 * @return Calculate salary of employee and return the same if employee with given @param empId exists
	 * @return 0.0, otherwise.
	 */
	public double getSalary(long empId) {

		Optional<Employee> optEmployee = employeeRepository.findById(empId);

		if (!optEmployee.isPresent()) {
			return 0.0;
		}

		Employee emp = optEmployee.get();

		return EmployeeHelper.calcEmployeeSalary(emp);
	}
}
