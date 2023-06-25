package com.inoptra.employeedepartmentdemo;

import com.inoptra.employeedepartmentdemo.helpers.DepartmentHelper;
import com.inoptra.employeedepartmentdemo.models.Department;
import com.inoptra.employeedepartmentdemo.models.Employee;
import com.inoptra.employeedepartmentdemo.repositories.DepartmentRepository;
import com.inoptra.employeedepartmentdemo.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTests {

    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentService(departmentRepository);
    }

    @Test
    void testGetAllDepartments() {
        // Arrange
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentRepository.findAll()).thenReturn(departments);

        // Act
        List<Department> result = departmentService.getAllDepartments();

        // Assert
        assertEquals(departments, result);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById_ExistingId() {
        // Arrange
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        // Act
        Department result = departmentService.getDepartmentById(departmentId);

        // Assert
        assertEquals(department, result);
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void testGetDepartmentById_NonExistingId() {
        // Arrange
        Long departmentId = 1L;
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // Act
        Department result = departmentService.getDepartmentById(departmentId);

        // Assert
        assertNull(result);
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void testCreateDepartment() {
        // Arrange
        Department department = new Department();
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        // Act
        Department result = departmentService.createDepartment(department);

        // Assert
        assertEquals(department, result);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testUpdateDepartment_ExistingId() {
        // Arrange
        Long departmentId = 1L;
        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);

        Department updatedDepartment = new Department();
        updatedDepartment.setId(departmentId);
        updatedDepartment.setName("Updated Department");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(existingDepartment)).thenReturn(updatedDepartment);

        // Act
        Department result = departmentService.updateDepartment(departmentId, updatedDepartment);

        // Assert
        assertNotNull(result);
        assertEquals(updatedDepartment, result);
        assertEquals("Updated Department", result.getName());
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, times(1)).save(existingDepartment);
    }

    @Test
    void testUpdateDepartment_NonExistingId() {
        // Arrange
        Long departmentId = 1L;
        Department updatedDepartment = new Department();
        updatedDepartment.setId(departmentId);

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // Act
        Department result = departmentService.updateDepartment(departmentId, updatedDepartment);

        // Assert
        assertNull(result);
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    void testDeleteDepartment_ExistingId() {
        // Arrange
        Long departmentId = 1L;

        // Act
        boolean result = departmentService.deleteDepartment(departmentId);

        // Assert
        assertTrue(result);
        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

    @Test
    void testDeleteDepartment_NonExistingId() {
        // Arrange
        Long departmentId = 1L;
        doThrow(new EmptyResultDataAccessException(1)).when(departmentRepository).deleteById(departmentId);

        // Act
        boolean result = departmentService.deleteDepartment(departmentId);

        // Assert
        assertFalse(result);
        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

    @Test
    void testGetAverageSalary_ExistingDepartment() {
        // Arrange
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);
        department.setEmployees(Arrays.asList(new Employee("John", 5000.0), new Employee("Jane", 6000.0)));

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        // Act
        double result = departmentService.getAverageSalary(departmentId);

        // Assert
        assertEquals(5500.0, result);
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void testGetAverageSalary_NonExistingDepartment() {
        // Arrange
        Long departmentId = 1L;
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // Act
        double result = departmentService.getAverageSalary(departmentId);

        // Assert
        assertEquals(0.0, result);
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void testGetTotalSalary_ExistingDepartment() {
        // Arrange
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);
        department.setEmployees(Arrays.asList(new Employee("John", 5000.0), new Employee("Jane", 6000.0)));

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        // Act
        double result = departmentService.getTotalSalary(departmentId);

        // Assert
        assertEquals(11000.0, result);
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void testGetTotalSalary_NonExistingDepartment() {
        // Arrange
        Long departmentId = 1L;
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // Act
        double result = departmentService.getTotalSalary(departmentId);

        // Assert
        assertEquals(0.0, result);
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void testGetAllTotalSalary() {
        // Arrange
        List<Department> departments = Arrays.asList(
                new Department("Accounting", Collections.singletonList(new Employee("John", 5000.0))),
                new Department("Billing", Collections.singletonList(new Employee("Jane", 6000.0))),
                new Department("Engineering", Collections.singletonList(new Employee("Bob", 4000.0)))
        );

        when(departmentRepository.findAll()).thenReturn(departments);

        // Act
        double result = departmentService.getAllTotalSalary();

        // Assert
        assertEquals(15000.0, result);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAvgSalary() {
        // Arrange
        List<Department> departments = Arrays.asList(
                new Department("Accounting", Collections.singletonList(new Employee("John", 5000.0))),
                new Department("Billing", Collections.singletonList(new Employee("Jane", 6000.0))),
                new Department("Engineering", Collections.singletonList(new Employee("Bob", 4000.0)))
        );

        when(departmentRepository.findAll()).thenReturn(departments);

        // Act
        double result = departmentService.getAllAvgSalary();

        // Assert
        assertEquals(5000.0, result);
        verify(departmentRepository, times(1)).findAll();
    }
}
