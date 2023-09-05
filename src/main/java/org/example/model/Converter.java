package org.example.model;

import static java.lang.Integer.parseInt;

public class Converter {
    public String convertToQuaternary(int decimalValue) {
        int remainder;
        StringBuilder convertedNumber = new StringBuilder();
        while (decimalValue > 0) {
            remainder = decimalValue % 4;
            decimalValue = decimalValue/4;
            convertedNumber.append(remainder);
        }
        convertedNumber.reverse();
        return convertedNumber.toString();
    }

    public int convertToDecimal(String quaternaryValue) {
        int result = 0;
        StringBuilder flippedString = new StringBuilder();
        flippedString.append(quaternaryValue);
        flippedString.reverse();
        String reverseString = flippedString.toString();
        for (int i = reverseString.length() - 1; i >=0; i--) {
            char currentCharacter = reverseString.charAt(i);
            result += parseInt(String.valueOf(currentCharacter)) * (int) Math.pow(4, i);
        }
        return result;
    }
}
