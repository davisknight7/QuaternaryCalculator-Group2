package org.example;

import org.example.model.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConverter {
    @Test
    void testConvertToDecimal() {
        Converter converter = new Converter();
        assertEquals(7, converter.convertToDecimal("13"));
    }

    @Test
    void testConvertToQuaternary() {
        Converter converter = new Converter();
        assertEquals("10", converter.convertToQuaternary(4));
    }

    @Test
    void testConvertToQuaternary_BigCase() {
        Converter converter = new Converter();
        assertEquals("1330", converter.convertToQuaternary(124));
    }
}
