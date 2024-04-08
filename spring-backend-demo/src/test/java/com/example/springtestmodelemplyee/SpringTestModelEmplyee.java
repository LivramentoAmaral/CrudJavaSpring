package com.example.springtestmodelemplyee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.springbackenddemo.model.Employee;

public class SpringTestModelEmplyee {

    @Test
    public void testGettersAndSetters() {
        // Criar um objeto Employee
        Employee employee = new Employee();

        // Definir valores usando os métodos setters
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");

        // Verificar se os valores podem ser recuperados corretamente usando os métodos getters
        assertEquals(1L, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmail());
    }
    
}
