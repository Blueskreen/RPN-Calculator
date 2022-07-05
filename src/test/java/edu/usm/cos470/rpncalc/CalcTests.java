package edu.usm.cos470.rpncalc;

import org.junit.jupiter.api.Test;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;
import edu.usm.cos470.rpncalc.exceptions.InvalidOperator;
import edu.usm.cos470.rpncalc.exceptions.OutOfOperands;

import org.junit.jupiter.api.Assertions;

public class CalcTests {

    @Test
    public void testConstructor() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        Assertions.assertEquals(testCalc.wordSize, 8);
        Assertions.assertNotNull(testCalc.stack);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Calculator testWordSize = new Calculator(65);});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Calculator testWordSize = new Calculator(3);});
    }

    @Test
    public void testPush() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        Assertions.assertEquals(testCalc.stack.pop().toString(), "4");
    }

    @Test
    public void testPop() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        Assertions.assertEquals(testCalc.pop(), "4");
        Assertions.assertThrows(OutOfOperands.class, () -> {testCalc.pop();});
    }

    @Test
    public void testPeek() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        testCalc.push("5");
        testCalc.push("6");
        Assertions.assertEquals(testCalc.peek(0), "6");
        Assertions.assertEquals(testCalc.peek(1), "5");
        Assertions.assertThrows(OutOfOperands.class, () -> {testCalc.peek(4);});
    }

    @Test
    public void testClear() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        Assertions.assertEquals(testCalc.pop(), "4");
        testCalc.clear();
        Assertions.assertEquals(testCalc.stack.size(), 0);
        testCalc.push("7");
        testCalc.execute("c");
        Assertions.assertEquals(testCalc.stack.size(), 0);
    }

    @Test
    public void testSize() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        testCalc.push("5");
        testCalc.push("6");
        Assertions.assertEquals(testCalc.size(), 3);
    }

    @Test
    public void testAdd() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        testCalc.push("5");
        testCalc.execute("+");
        Assertions.assertEquals(testCalc.pop(), "9");
    }

    @Test
    public void testSubtract() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("10");
        testCalc.push("7");
        testCalc.execute("-");
        Assertions.assertEquals(testCalc.pop(), "3");
    }

    @Test
    public void testMultiply() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        testCalc.push("5");
        testCalc.execute("*");
        Assertions.assertEquals(testCalc.pop(), "20");
    }

    @Test
    public void testDivide() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("50");
        testCalc.push("10");
        testCalc.execute("/");
        Assertions.assertEquals(testCalc.pop(), "5");
    }

    @Test
    public void divideByZeroTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Calculator testCalc = new Calculator(8);
            testCalc.push("5");
            testCalc.push("0");
            testCalc.execute("/");});
    }

    @Test
    public void testModulus() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("21");
        testCalc.push("10");
        testCalc.execute("%");
        Assertions.assertEquals(testCalc.pop(), "1");
    }
    
    @Test
    public void testLargeWordSize() throws CalculatorException {
    	Calculator testCalc = new Calculator(64);
    	String twoTo48 = "562949953421312";
    	testCalc.push(twoTo48);
    	testCalc.push("2");
    	testCalc.execute("+");
    	Assertions.assertEquals("562949953421314", testCalc.pop());
    }
    
    @Test
    public void testOverflow() throws CalculatorException{
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("255");
    	testCalc.push("2");
    	testCalc.execute("+");
    	Assertions.assertTrue(testCalc.overflowFlag);
    	Assertions.assertEquals("O", testCalc.flags());
    	testCalc.pop();
    	testCalc.push("2");
    	testCalc.push("2");
    	testCalc.execute("+");
    	Assertions.assertTrue(!testCalc.overflowFlag);
    	Assertions.assertEquals("o", testCalc.flags());
    }
    
    @Test
    public void testInvalidOperator() throws CalculatorException{
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("2");
    	testCalc.push("2");
    	Assertions.assertThrows(InvalidOperator.class, () -> {testCalc.execute("~");});
    }
    
    @Test
    public void PushingSizeOnStack() throws CalculatorException{
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("255");
    	testCalc.push("2");
    	testCalc.push("2");
    	testCalc.push("2");
    	testCalc.execute("s");
    	Assertions.assertEquals("4", testCalc.pop());
    }
    
    @Test
    public void testDuplicate() throws CalculatorException {
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("2");
    	testCalc.execute("d");
    	Assertions.assertTrue(testCalc.size() == 2 && testCalc.pop().equals("2"));
    }
    
    @Test
    public void testDuplicateErrors() throws CalculatorException {
    	Calculator testCalc = new Calculator(8);
    	Assertions.assertThrows(OutOfOperands.class, () -> {
    		testCalc.execute("d");
    	});
    }
    
    @Test
    public void testReverse() throws CalculatorException {
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("2");
    	testCalc.push("3");
    	testCalc.execute("r");
    	testCalc.execute("-");
    	Assertions.assertEquals("1", testCalc.pop());
    }
    
    @Test
    public void testReverseErrors() throws CalculatorException {
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("1");
    	Assertions.assertThrows(OutOfOperands.class, () -> {
    		testCalc.execute("r");
    	});   	
    }
    
    @Test
    public void testOperationOnEmptyStack() throws CalculatorException {
    	Calculator testCalc = new Calculator(8);
    	Assertions.assertThrows(OutOfOperands.class, () -> {
    		testCalc.execute("+");
    	});   	
    }
    
    @Test
    public void testOperationWithNotEnough() throws CalculatorException {
    	Calculator testCalc = new Calculator(8);
    	testCalc.push("1");
    	Assertions.assertThrows(OutOfOperands.class, () -> {
    		testCalc.execute("+");
    	});   	
    }
    
}
