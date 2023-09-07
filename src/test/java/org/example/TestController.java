package org.example;

import org.example.controller.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestController {
    private final Controller controller = new Controller();

    @Test
    void testCompute_addCaseOne() {
        Assertions.assertEquals("21", controller.compute("10+11"));
    }

    @Test
    void testCompute_addCaseTwo() {
        Assertions.assertEquals("300", controller.compute("133+101"));
    }

    @Test
    void testCompute_subtractCaseOne() {
        Assertions.assertEquals("2", controller.compute("11-3"));
    }

    @Test
    void testCompute_subtractCaseTwo() {
        Assertions.assertEquals("11", controller.compute("113-102"));
    }

    @Test
    void testCompute_multiplyCase() {
        Assertions.assertEquals("32", controller.compute("13*2"));
    }

    @Test
    void testCompute_divideCase() {
        Assertions.assertEquals("10", controller.compute("30/3"));
    }

    @Test
    void testCompute_divideByZeroCase() {


        Assertions.assertThrows(ArithmeticException.class, ()->controller.compute("01/0"));

    }


    @Test
    void testCompute_powerCaseOne() {
        Assertions.assertEquals("10", controller.compute("2^2"));
    }

    @Test
    void testCompute_powerCaseTwo() {
        Assertions.assertEquals("3210301", controller.compute("23^10"));
    }
}
