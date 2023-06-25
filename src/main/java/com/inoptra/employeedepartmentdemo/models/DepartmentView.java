package com.inoptra.employeedepartmentdemo.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentView {

    public DepartmentView() {
    }

    public DepartmentView(Long id, String name, List<EmployeeView> employees){
        this.name = name;
        this.employees = employees;
    }

    private Long id;

    private String name;

    private List<EmployeeView> employees;

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

    public List<EmployeeView> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeView> employees) {
        this.employees = employees;
    }

    public static DepartmentView from(Department department){
        DepartmentView view = new DepartmentView();
        if(department.getEmployees() != null) {
            view.employees = department.getEmployees().stream().map(EmployeeView::from)
                    .collect(Collectors.toList());
        }
        view.name = department.getName();
        view.id = department.getId();
        return view;
    }
}
