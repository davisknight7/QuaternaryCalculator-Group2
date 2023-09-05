package org.example;

import org.example.model.Calculator;
import org.example.model.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalculator {
    private final Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(2, calculator.add(1, 1));
    }

    @Test
    void testSubtraction() {
        assertEquals(4, calculator.sub(5, 1));
    }

    @Test
    void testMultiplication() {
        assertEquals(8, calculator.multiply(4, 2));
    }

    @Test
    void testDivide() {
        assertEquals(3, calculator.divide(9, 3));
    }

    @Test
    void testQuatAdd() {
        Converter converter = new Converter();
        int x = converter.convertToDecimal("1330");
        int y = converter.convertToDecimal("13");
        assertEquals(131, calculator.add(x, y));
    }
}
