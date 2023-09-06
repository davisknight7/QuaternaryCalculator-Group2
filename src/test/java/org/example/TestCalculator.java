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

    @Test
    public void testSquare() {

        // Test square of a positive number
        assertEquals(25, calculator.square(5));

        // Test square of zero
        assertEquals(0, calculator.square(0));

        // Test square of a negative number
        assertEquals(25, calculator.square(-5));
    }

    @Test
    public void testSquareRoot() {
        // Test square root of a positive number
        assertEquals(5.0, calculator.squareRoot(25), 0.001);

        // Test square root of zero
        assertEquals(0.0, calculator.squareRoot(0), 0.001);
    }
}

