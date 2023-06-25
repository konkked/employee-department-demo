package com.inoptra.employeedepartmentdemo.helpers;

import com.inoptra.employeedepartmentdemo.models.Employee;

public class EmployeeHelper {
    private EmployeeHelper() {
    }

    public static double calcEmployeeSalary(Employee employee) {
        return employee
                .getSalary()
                .getSalaryComponents()
                .parallelStream()
                .reduce(
                        0.0,
                        (total, sc) -> total + (sc.getFactor() *  employee.getSalary().getBaseSalary()),
                        Double::sum
                );
    }
}
