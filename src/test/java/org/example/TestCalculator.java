package org.example;

import org.example.model.Calculator;
import org.example.model.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalculator {
    private final Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals("2", calculator.calculate(1, 1, "+"));
    }

    @Test
    void testSubtraction() {
        assertEquals("10", calculator.calculate(5, 1, "-"));
    }

    @Test
    void testMultiplication() {
        assertEquals("20", calculator.calculate(4, 2, "*"));
    }

    @Test
    void testDivide() {
        assertEquals("3", calculator.calculate(9, 3, "/"));
    }

    @Test
    void testQuatAdd() {
        Converter converter = new Converter();
        int x = converter.convertToDecimal("1330");
        int y = converter.convertToDecimal("13");
        assertEquals("2003", calculator.calculate(x, y, "+"));
    }

    @Test
    public void testSquare() {
        // Test square of a positive number
        assertEquals("121", calculator.calculate(5, 2, "^"));
    }
    @Test
    public void testSquareZero() {
        assertEquals("0", calculator.calculate(0, 2, "^"));
    }

    @Test
    public void testSquareRoot() {
        // Test square root of a positive number
        assertEquals("11", calculator.calculate(25, 1, "sqrt"));

    }
}

