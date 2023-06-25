package com.inoptra.employeedepartmentdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoptra.employeedepartmentdemo.controllers.DepartmentController;
import com.inoptra.employeedepartmentdemo.models.Department;
import com.inoptra.employeedepartmentdemo.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc
public class DepartmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    // Existing employee controller tests...

    @Test
    void shouldReturnAllDepartments() throws Exception {
        // Arrange
        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("Department 1");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("Department 2");

        List<Department> departments = Arrays.asList(department1, department2);

        when(departmentService.getAllDepartments()).thenReturn(departments);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(departments)));
    }

    @Test
    void shouldReturnDepartmentById() throws Exception {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 1");

        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(department)));
    }

    @Test
    void shouldCreateDepartment() throws Exception {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 1");

        when(departmentService.createDepartment(any(Department.class))).thenReturn(department);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(department)));
    }

    @Test
    void shouldUpdateDepartment() throws Exception {
        // Arrange
        Department existingDepartment = new Department();
        existingDepartment.setId(1L);
        existingDepartment.setName("Department 1");

        Department updatedDepartment = new Department();
        updatedDepartment.setId(1L);
        updatedDepartment.setName("Department 2");

        when(departmentService.updateDepartment(eq(1L), any()))
                .thenReturn(updatedDepartment);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDepartment)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedDepartment)));
    }

    @Test
    void shouldDeleteDepartment() throws Exception {
        // Arrange
        when(departmentService.deleteDepartment(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }
}
