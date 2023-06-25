package com.inoptra.employeedepartmentdemo;

import com.inoptra.employeedepartmentdemo.helpers.EmployeeHelper;
import com.inoptra.employeedepartmentdemo.models.Department;
import com.inoptra.employeedepartmentdemo.models.Employee;
import com.inoptra.employeedepartmentdemo.repositories.EmployeeRepository;
import com.inoptra.employeedepartmentdemo.repositories.SalaryRepository;
import com.inoptra.employeedepartmentdemo.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private SalaryRepository salaryRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        List<Employee> employees = Arrays.asList(
                new Employee("John", 5000.0),
                new Employee("Jane", 6000.0),
                new Employee("Bob", 4000.0)
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(employees, result);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById_ExistingId() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee("John", 5000.0);
        employee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeService.getEmployeeById(employeeId);

        // Assert
        assertEquals(employee, result);
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void testGetEmployeeById_NonExistingId() {
        // Arrange
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act
        Employee result = employeeService.getEmployeeById(employeeId);

        // Assert
        assertNull(result);
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        Employee employee = new Employee("John", 5000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee result = employeeService.createEmployee(employee);

        // Assert
        assertEquals(employee, result);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testUpdateEmployee_ExistingId() {
        // Arrange
        Long employeeId = 1L;
        Employee existingEmployee = new Employee("John", 5000.0);
        existingEmployee.setId(employeeId);

        Employee updatedEmployee = new Employee("Jane", 6000.0);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existingEmployee);

        // Act
        Employee result = employeeService.updateEmployee(employeeId, updatedEmployee);

        // Assert
        assertEquals(existingEmployee, result);
        assertEquals(updatedEmployee.getName(), existingEmployee.getName());
        assertEquals(updatedEmployee.getSalary(), existingEmployee.getSalary());
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void testUpdateEmployee_NonExistingId() {
        // Arrange
        Long employeeId = 1L;
        Employee updatedEmployee = new Employee("Jane", 6000.0);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act
        Employee result = employeeService.updateEmployee(employeeId, updatedEmployee);

        // Assert
        assertNull(result);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_ExistingId() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee("John", 5000.0);
        employee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Act
        boolean result = employeeService.deleteEmployee(employeeId);

        // Assert
        assertTrue(result);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void testDeleteEmployee_NonExistingId() {
        // Arrange
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act
        boolean result = employeeService.deleteEmployee(employeeId);

        // Assert
        assertFalse(result);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, never()).deleteById(any(Long.class));
    }

    @Test
    void testGetSalary_ExistingEmployee() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee("John", 5000.0);
        employee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // Act
        double result = employeeService.getSalary(employeeId);

        // Assert
        assertEquals(5000.0, result);
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void testGetSalary_NonExistingEmployee() {
        // Arrange
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act
        double result = employeeService.getSalary(employeeId);

        // Assert
        assertEquals(0.0, result);
        verify(employeeRepository, times(1)).findById(employeeId);
    }
}
