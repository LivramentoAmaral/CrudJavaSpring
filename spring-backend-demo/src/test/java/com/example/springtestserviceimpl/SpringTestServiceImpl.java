package com.example.springtestserviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.springbackenddemo.exception.ResourceNotFoundException;
import com.example.springbackenddemo.model.Employee;
import com.example.springbackenddemo.repository.EmployeeRepository;
import com.example.springbackenddemo.service.impl.EmployeeServiceImpl;
import com.example.springbackenddemo.SpringBackendDemoApplication;

@SpringBootTest(classes = SpringBackendDemoApplication.class)
public class SpringTestServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        // Limpa todos os funcionários antes de cada teste
        employeeRepository.deleteAll();
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertNotNull(savedEmployee.getId());
        assertEquals("John", savedEmployee.getFirstName());
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("John");

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setFirstName("John");

        Employee savedEmployee = employeeRepository.save(employee);

        Employee foundEmployee = employeeService.getEmployeeById(savedEmployee.getId());

        assertNotNull(foundEmployee);
        assertEquals(savedEmployee.getId(), foundEmployee.getId());
        assertEquals("John", foundEmployee.getFirstName());
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");

        Employee savedEmployee = employeeRepository.save(employee);

        savedEmployee.setFirstName("Jane");
        Employee updatedEmployee = employeeService.updateEmployee(savedEmployee, savedEmployee.getId());

        assertEquals(savedEmployee.getId(), updatedEmployee.getId());
        assertEquals("Jane", updatedEmployee.getFirstName());
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");

        Employee savedEmployee = employeeRepository.save(employee);

        employeeService.deleteEmployee(savedEmployee.getId());

        assertFalse(employeeRepository.existsById(savedEmployee.getId()));
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(1L);
        });
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        Employee employee = new Employee();
        employee.setId(1L);

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updateEmployee(employee, employee.getId());
        });
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.deleteEmployee(1L);
        });
    }
}
