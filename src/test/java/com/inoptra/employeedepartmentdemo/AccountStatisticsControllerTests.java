package com.inoptra.employeedepartmentdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoptra.employeedepartmentdemo.controllers.AccountStatisticsController;
import com.inoptra.employeedepartmentdemo.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(AccountStatisticsController.class)
@AutoConfigureMockMvc
public class AccountStatisticsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void shouldReturnTotalSalaryForAllDepartments() throws Exception {
        // Arrange
        double totalSalary = 10000.0;
        when(departmentService.getAllTotalSalary()).thenReturn(totalSalary);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/account/accountstats/all/total"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(totalSalary)));
    }

    @Test
    void shouldReturnTotalSalaryForDepartment() throws Exception {
        // Arrange
        int deptId = 1;
        double totalSalary = 5000.0;
        when(departmentService.getTotalSalary(deptId)).thenReturn(totalSalary);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/account/accountstats/{deptId}/total", deptId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(totalSalary)));
    }

    @Test
    void shouldReturnAverageSalaryForAllDepartments() throws Exception {
        // Arrange
        double averageSalary = 5000.0;
        when(departmentService.getAllAvgSalary()).thenReturn(averageSalary);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/account/accountstats/all/avg"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(averageSalary)));
    }

    @Test
    void shouldReturnAverageSalaryForDepartment() throws Exception {
        // Arrange
        int deptId = 1;
        double averageSalary = 2500.0;
        when(departmentService.getAverageSalary(deptId)).thenReturn(averageSalary);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/account/accountstats/{deptId}/avg", deptId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(averageSalary)));
    }
}
