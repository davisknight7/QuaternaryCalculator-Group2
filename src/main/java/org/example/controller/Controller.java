package org.example.controller;

import org.example.model.Converter;
import org.example.model.Parser;

public class Controller {
    private final Converter converter = new Converter();

    public String compute(String input) throws ArithmeticException {

        Parser parser = new Parser();
        String[] equation = parser.parse(input);
        return computeAnswer(equation);
    }

    private String computeAnswer(String[] equation) {
        int first = converter.convertToDecimal(equation[0]);
        int second = converter.convertToDecimal(equation[2]);
        switch(equation[1]) {
            case "+":
                return converter.convertToQuaternary(first + second);
            case "-":
                return converter.convertToQuaternary(first - second);
            case "*":
                return converter.convertToQuaternary(first * second);
            case "/":
                return converter.convertToQuaternary(first / second);
            case "^":
                return converter.convertToQuaternary((int)Math.pow(first, second));
            default:
                return "";
        }
    }

    public String convert(String number) {
        return String.valueOf(converter.convertToDecimal(number));
    }
}
