package org.example.model;

public class  Calculator {
    public String calculate(int first, int second, String operation){
        Converter converter = new Converter();
        switch (operation) {
            case "+" -> {
                return converter.convertToQuaternary(first + second);
            }
            case "-" -> {
                return converter.convertToQuaternary(first - second);
            }
            case "*" -> {
                return converter.convertToQuaternary(first * second);
            }
            case "/" -> {
                return converter.convertToQuaternary(first / second);
            }
            case "^" -> {
                return converter.convertToQuaternary((int) Math.pow(first, second));
            }
            case "\u221A" -> {
                double num = Math.sqrt(first);
                if (num % 1 == 0) {
                    return converter.convertToQuaternary((int) Math.sqrt(first));
                }
                return "No sqrt";
            }
            default -> {
                return "";
            }
        }
    }
}
