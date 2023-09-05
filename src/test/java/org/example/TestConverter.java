package org.example;

import org.example.model.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConverter {
    private final Converter converter = new Converter();

    @Test
    void testConvertToDecimal() {
        assertEquals(7, converter.convertToDecimal("13"));
    }

    @Test
    void testConvertToDecimal_sanity() {
        assertEquals(124, converter.convertToDecimal("1330"));
    }

    @Test
    void testConvertToQuaternary() {
        assertEquals("10", converter.convertToQuaternary(4));
    }

    @Test
    void testConvertToQuaternary_BigCase() {
        assertEquals("1330", converter.convertToQuaternary(124));
    }
}
