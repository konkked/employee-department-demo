package com.inoptra.employeedepartmentdemo.helpers;

import com.inoptra.employeedepartmentdemo.models.Department;

public class DepartmentHelper {
    private DepartmentHelper() {

    }

    public static double calcAvgSalary(Department department) {
        double size = Double.valueOf(department.getEmployees().size());

        return department.getEmployees().stream().reduce(
                Double.valueOf(0.0),
                (total,empl)->total+ EmployeeHelper.calcEmployeeSalary(empl)/size,
                (c1,c2) -> c1 + c2);
    }

    public static double calcTotalSalary(Department department) {
        return department.getEmployees().stream().reduce(
                Double.valueOf(0.0),
                (total,empl)->total+ EmployeeHelper.calcEmployeeSalary(empl),
                (c1,c2) -> c1 + c2);
    }
}
