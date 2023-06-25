package com.inoptra.employeedepartmentdemo.repositories;

import com.inoptra.employeedepartmentdemo.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Charles Keyser
 * @Description:
 * Persistence layer which performs operations on Department entity
 **/
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
