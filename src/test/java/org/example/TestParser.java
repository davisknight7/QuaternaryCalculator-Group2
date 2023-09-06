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
}
