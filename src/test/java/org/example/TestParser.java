package org.example;

import org.example.model.Converter;
import org.example.model.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
public class TestParser {
    private final Parser parser = new Parser();

    @Test
    void testParseAddition(){
        String[] expected = new String[]{"01", "+", "10"};
        assertArrayEquals(expected, parser.parse("01+10"));
    }

    @Test
    void testParseSubtraction(){
        String[] expected = new String[]{"03", "-", "30"};
        assertArrayEquals(expected, parser.parse("03-30"));
    }

    @Test
    void testParseMultiplication(){
        String[] expected = new String[]{"13", "*", "31"};
        assertArrayEquals(expected, parser.parse("13*31"));
    }

    @Test
    void testParseDivision(){
        String[] expected = new String[]{"1000", "/", "3333"};
        assertArrayEquals(expected, parser.parse("1000/3333"));
    }

    @Test
    void testParsePower() {
        String[] expected = new String[]{"10", "^", "2"};
        assertArrayEquals(expected, parser.parse("10^2"));
    }
}
