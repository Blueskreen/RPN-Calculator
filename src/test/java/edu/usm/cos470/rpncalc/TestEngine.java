package edu.usm.cos470.rpncalc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestEngine {
    @Test
    public void example() {
        int i = 0b101;
        System.out.println(i);
        Assertions.assertEquals(JunitTest.testMe(i), i+1);
    }

    @Test
    public void multiplication(){
        int i = 3;
        Assertions.assertEquals(JunitTest.multiply(i), i*3);
    }
}
