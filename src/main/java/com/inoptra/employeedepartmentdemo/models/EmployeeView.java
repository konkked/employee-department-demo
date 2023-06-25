package com.inoptra.employeedepartmentdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inoptra.employeedepartmentdemo.controllers.EmployeeController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Represents Employee entity
 **/
public class EmployeeView {

    public EmployeeView() {

    }

    public EmployeeView(Long id, String name, Salary salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    private Long id;

    private String name;

    private Salary salary;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public static EmployeeView from(Employee employee) {
        EmployeeView view = new EmployeeView();
        view.id = employee.getId();
        view.name = employee.getName();
        view.salary = employee.getSalary();
        return view;
    }
}
