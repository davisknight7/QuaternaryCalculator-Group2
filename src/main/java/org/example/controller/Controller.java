package org.example.controller;

import org.example.model.Calculator;
import org.example.model.Converter;
import org.example.model.Parser;

import static java.lang.Integer.parseInt;

public class Controller {
    private final Converter converter = new Converter();

    public String compute(String input) throws ArithmeticException {
        Parser parser = new Parser();
        String[] equation = parser.parse(input);
        Calculator calculator = new Calculator();
        int first = converter.convertToDecimal(equation[0]);
        int second = converter.convertToDecimal(equation[2]);
        return calculator.calculate(first, second, equation[1]);
    }

    public String convert(String number) {
        return String.valueOf(converter.convertToDecimal(number));
    }

    public String convertBack(String number){
        return String.valueOf(converter.convertToQuaternary(parseInt(number)));
    }
}
