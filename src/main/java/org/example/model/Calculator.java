package org.example.model;

public class Calculator {
    public int add(int x, int y) {
        return x + y;
    }

    public int sub(int x, int y) {
        return x - y;
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    public int divide(int x, int y) {
        return x / y;
    }

    public double squareRoot(int x){
        if(x<0){
            throw new IllegalArgumentException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(x);
    }

    public int square(int x){
        return x * x;
    }
}
