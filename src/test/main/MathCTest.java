package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathCTest {
    MathC mathC = new MathC();
    @Test
    void add() {
        assertEquals(20,mathC.add(10,10));
    }
}

