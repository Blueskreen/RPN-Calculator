package edu.usm.cos470.rpncalc;

import org.junit.jupiter.api.Test;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;

import org.junit.jupiter.api.Assertions;

public class BitwiseTests {

    @Test
    public void bitwiseAnd() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("25");
        testCalc.execute("&");
        Assertions.assertEquals(testCalc.pop(), "8");
    }
    @Test
    void bitwiseOr() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("25");
        testCalc.execute("|");
        Assertions.assertEquals(testCalc.pop(), "29");
    }
    @Test
    void bitwiseXOr() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("12");
        testCalc.push("25");
        testCalc.execute("^");
        Assertions.assertEquals(testCalc.pop(), "21");
    }
    @Test
    void bitwiseRightShift() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("8");
        testCalc.push("1");
        testCalc.execute(">>");
        Assertions.assertEquals(testCalc.pop(), "4");
    }
    @Test
    void bitwiseLeftShift() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        testCalc.push("2");
        testCalc.execute("<<");
        Assertions.assertEquals(testCalc.pop(), "16");
    }
    
    @Test
    public void testLeftShiftAtUpperLimit() throws CalculatorException {
    	int expected = (255 << 1) & 0b11111111;
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("255");
    	testCalc.push("1");
    	testCalc.execute("<<");
    	Assertions.assertEquals(Integer.valueOf(expected).toString(), testCalc.pop());
    }
    
    @Test
    public void testRightShiftAtLowerLimit() throws CalculatorException{
    	int expected = 1 >> 1;
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("1");
    	testCalc.push("1");
    	testCalc.execute(">>");
    	Assertions.assertEquals(Integer.valueOf(expected).toString(), testCalc.pop());
    }
}
