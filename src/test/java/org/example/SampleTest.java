package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

    @Test
    void getNumber() {
        Sample sample = new Sample();
        assertEquals(1, sample.getNumber());
    }
}