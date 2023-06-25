package com.inoptra.employeedepartmentdemo.services;

import com.inoptra.employeedepartmentdemo.helpers.DepartmentHelper;
import com.inoptra.employeedepartmentdemo.models.Department;
import com.inoptra.employeedepartmentdemo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Service layer contract which supports operations on Department object
 **/
@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setName(updatedDepartment.getName());
            department.setEmployees(updatedDepartment.getEmployees());
            return departmentRepository.save(department);
        }
        return null;
    }

    public boolean deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


    /**
     * Compute and return average salary for the given department
     * @param departmentId ID of the department
     * @return Average salary of the department if it exists, 0.0 otherwise
     */
    public double getAverageSalary(long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (!optionalDepartment.isPresent()) {
            return 0.0;
        }

        Department department = optionalDepartment.get();
        return DepartmentHelper.calcAvgSalary(department);
    }

    /**
     * Compute and return total salary for the given department
     * @param departmentId ID of the department
     * @return Total salary of the department if it exists, 0.0 otherwise
     */
    public double getTotalSalary(long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (!optionalDepartment.isPresent()) {
            return 0.0;
        }

        Department department = optionalDepartment.get();
        return DepartmentHelper.calcTotalSalary(department);
    }

    /**
     * Compute and return the total salary for all departments
     * @return Total salary for all departments
     */
    public double getAllTotalSalary() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .mapToDouble(DepartmentHelper::calcTotalSalary)
                .sum();
    }

    /**
     * Compute and return the average salary for all departments
     * @return Average salary for all departments
     */
    public double getAllAvgSalary() {
        List<Department> departments = departmentRepository.findAll();
        double totalEmployees = departments.stream()
                .mapToInt(dept -> dept.getEmployees().size())
                .sum();

        return departments.stream()
                .mapToDouble(dept -> DepartmentHelper.calcAvgSalary(dept) * (dept.getEmployees().size() / totalEmployees))
                .sum();
    }
}
