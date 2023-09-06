package org.example;

import org.example.model.Converter;
import org.example.model.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
public class TestParser {

    @Test
    void testParseAddition(){
        Parser parser = new Parser();
        String[] expected = new String[]{"01", "+", "10"};
        assertArrayEquals(expected, parser.parse("01+10"));
    }

    @Test
    void testParseSubtraction(){
        Parser parser = new Parser();
        String[] expected = new String[]{"03", "-", "30"};
        assertArrayEquals(expected, parser.parse("03-30"));
    }

    @Test
    void testParseMultiplication(){
        Parser parser = new Parser();
        String[] expected = new String[]{"13", "*", "31"};
        assertArrayEquals(expected, parser.parse("13*31"));
    }

    @Test
    void testParseDivision(){
        Parser parser = new Parser();
        String[] expected = new String[]{"01", "/", "10"};
        assertArrayEquals(expected, parser.parse("01/10"));
    }
}
