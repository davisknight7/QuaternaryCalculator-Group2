package org.example.model;

public class Calculator {
    public String calculate(int first, int second, String operation){
        Converter converter = new Converter();
        switch(operation) {
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
}
