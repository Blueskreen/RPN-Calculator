package edu.usm.cos470.rpncalc;
import org.junit.jupiter.api.Test;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;

import org.junit.jupiter.api.Assertions;

public class RelationalTests {

    @Test
    public void equalsTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("12");
        testCalc.execute("==");
        Assertions.assertEquals(testCalc.pop(), "1");
        testCalc.clear();
        testCalc.push("12");
        testCalc.push("13");
        testCalc.execute("==");
        Assertions.assertEquals("0", testCalc.pop());
    }

    @Test
    public void notEqualsTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("12");
        testCalc.execute("!=");
        Assertions.assertEquals(testCalc.pop(), "0");
        testCalc.clear();
        testCalc.push("12");
        testCalc.push("13");
        testCalc.execute("!=");
        Assertions.assertEquals("1", testCalc.pop());
    }
    @Test
    public void greaterTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("50");
        testCalc.push("12");
        testCalc.execute(">");
        Assertions.assertEquals(testCalc.pop(), "1");
        testCalc.clear();
        testCalc.push("34");
        testCalc.push("35");
        testCalc.execute(">");
        Assertions.assertEquals("0", testCalc.pop());
    }

    @Test
    public void lessTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("1");
        testCalc.execute("<");
        Assertions.assertEquals(testCalc.pop(), "0");
        testCalc.clear();
        testCalc.push("12");
        testCalc.push("13");
        testCalc.execute("<");
        Assertions.assertEquals("1", testCalc.pop());
    }
    @Test
    public void greaterOrEqualTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("12");
        testCalc.execute(">=");
        Assertions.assertEquals(testCalc.pop(), "1");
        testCalc.clear();
        testCalc.push("12");
        testCalc.push("13");
        testCalc.execute(">=");
        Assertions.assertEquals("0", testCalc.pop());
        testCalc.push("13");
        testCalc.push("12");
        testCalc.execute(">=");
        Assertions.assertEquals(testCalc.pop(), "1");
        testCalc.clear();
    }
    @Test
    public void lessOrEqualTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("12");
        testCalc.execute("<=");
        Assertions.assertEquals(testCalc.pop(), "1");
        testCalc.clear();
        testCalc.push("100");
        testCalc.push("200");
        testCalc.execute("<=");
        Assertions.assertEquals("1", testCalc.pop());
        testCalc.push("13");
        testCalc.push("12");
        testCalc.execute("<=");
        Assertions.assertEquals(testCalc.pop(), "0");
        testCalc.clear();
    }
}
