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
}
