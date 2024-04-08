package com.example.springtestexpetion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.springbackenddemo.exception.ResourceNotFoundException;

public class SpringTestExpetion {
    @Test
    public void testResourceNotFoundExceptionMessage() {
        String resourceName = "Employee";
        String fieldName = "id";
        Object fieldValue = 1;

        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        String expectedMessage = "Employee not found with id : 1";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionGetters() {
        String resourceName = "Product";
        String fieldName = "name";
        Object fieldValue = "Chair";

        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        assertEquals(resourceName, exception.getResourceName());
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(fieldValue, exception.getFieldValue());
    }
}
