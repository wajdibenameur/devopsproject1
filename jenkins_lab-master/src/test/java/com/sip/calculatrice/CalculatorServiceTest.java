package com.sip.calculatrice;

import org.junit.jupiter.api.Test;

import com.sip.calculatrice.services.CalculatorService;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAdd() {
        assertEquals(5, calculatorService.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, calculatorService.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(6, calculatorService.multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculatorService.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(5, 0);
        });
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}