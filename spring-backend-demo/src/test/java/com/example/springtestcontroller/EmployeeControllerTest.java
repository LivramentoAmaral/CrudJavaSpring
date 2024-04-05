package com.example.springtestcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.springbackenddemo.controller.EmployeeController;
import com.example.springbackenddemo.model.Employee;
import com.example.springbackenddemo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEmployeeById() {
        long employeeId = 1;
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setFirstName("John Doe");

        when(employeeService.getEmployeeById(employeeId)).thenReturn(mockEmployee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployee, response.getBody());
    }

    @Test

    public void testGetAllEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee());
        mockEmployees.add(new Employee());
        mockEmployees.add(new Employee());

        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        List<Employee> response = employeeController.getAllEmployees();

        assertEquals(mockEmployees, response);
    }

    @Test

    public void testSaveEmployee() {
        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("John Doe");

        when(employeeService.saveEmployee(mockEmployee)).thenReturn(mockEmployee);

        ResponseEntity<Employee> response = employeeController.saveEmployee(mockEmployee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockEmployee, response.getBody());
    }

    @Test

    public void testUpdateEmployee() {
        long employeeId = 1;
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setFirstName("John Doe");

        when(employeeService.updateEmployee(mockEmployee, employeeId)).thenReturn(mockEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(employeeId, mockEmployee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployee, response.getBody());
    }

    @Test

    public void testDeleteEmployee() {
        long employeeId = 1;

        ResponseEntity<String> response = employeeController.deleteEmployee(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Funcinário deletado com sucesso!", response.getBody());
    }

    // Teste outros métodos conforme necessário
}
