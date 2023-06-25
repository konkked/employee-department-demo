package com.inoptra.employeedepartmentdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoptra.employeedepartmentdemo.controllers.DepartmentController;
import com.inoptra.employeedepartmentdemo.controllers.EmployeeController;
import com.inoptra.employeedepartmentdemo.models.Employee;
import com.inoptra.employeedepartmentdemo.models.EmployeeView;
import com.inoptra.employeedepartmentdemo.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldReturnAllEmployees() throws Exception {
		// Arrange
		Employee employee1 = new Employee();
		employee1.setId(1L);
		employee1.setName("John Doe");

		Employee employee2 = new Employee();
		employee2.setId(2L);
		employee2.setName("Jane Smith");

		List<Employee> employees = Arrays.asList(employee1, employee2);

		when(employeeService.getAllEmployees()).thenReturn(employees);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(
						objectMapper.writeValueAsString(employees.stream()
								.map(EmployeeView::from).collect(Collectors.toList()))))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void shouldReturnEmployeeById() throws Exception {
		// Arrange
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("John Doe");

		when(employeeService.getEmployeeById(1L)).thenReturn(employee);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json(objectMapper.writeValueAsString(EmployeeView.from(employee))))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void shouldCreateEmployee() throws Exception {
		// Arrange
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("John Doe");

		when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/employees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employee)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().json(
						objectMapper.writeValueAsString(EmployeeView.from(employee))))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void shouldUpdateEmployee() throws Exception {
		// Arrange
		Employee existingEmployee = new Employee();
		existingEmployee.setId(1L);
		existingEmployee.setName("John Doe");

		Employee updatedEmployee = new Employee();
		updatedEmployee.setId(1L);
		updatedEmployee.setName("Jane Smith");

		when(employeeService.updateEmployee(eq(1L), any()))
				.thenReturn(updatedEmployee);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updatedEmployee)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(
						objectMapper.writeValueAsString(EmployeeView.from(updatedEmployee))))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void shouldDeleteEmployee() throws Exception {
		// Arrange
		when(employeeService.deleteEmployee(1L)).thenReturn(true);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andExpect(MockMvcResultMatchers.content().string(""))
				.andDo(MockMvcResultHandlers.print());
	}
}
