package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<String> operators = List.of("+", "-","/","*");

    public String[] parse(String operation){
        String[] parsedValues = new String[3];

        StringBuilder first = new StringBuilder();
        for (int i=0; i < operation.length();i++){
            if (operators.contains(String.valueOf(operation.charAt(i)))){
                parsedValues[0] = first.toString();
                parsedValues[1] = String.valueOf(operation.charAt(i));
                parsedValues[2] = operation.substring(i+1);
                break;
            }
            first.append(operation.charAt(i));
        }

        return parsedValues;
    }
}
