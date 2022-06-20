package com.inoptra.employeedepartmentdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inoptra.employeedepartmentdemo.models.Department;

/**
 * @Author: Shrikrishna Prabhumirashi
 * @Description:
 * Persistence layer which performs operations on Department entity
 **/
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
