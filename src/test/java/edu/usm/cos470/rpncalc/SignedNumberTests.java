package edu.usm.cos470.rpncalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;

public class SignedNumberTests {
	
	Calculator testCalc;
	String negativeOne = "-1";
	
	@BeforeEach
	public void setup() throws CalculatorException {
		testCalc = new Calculator(8);
	}
	
	@Test
	public void testStackNumberSignedValues() throws CalculatorException {
		StackNumber one = new StackNumber(1l, false);
		StackNumber two = new StackNumber(2l);
		assertTrue(!one.getIsNegative());
		assertTrue(!two.getIsNegative());
		
		StackNumber test = new StackNumber(255l);
		test.setIsNegative(true);
		assertTrue(test.getIsNegative());
		
		test.setValue(256L, test.getIsNegative());
		test.setIsNegative(false);
		assertEquals(-256, test.getValue());
	}
	
	@Test
	public void testModeChange() throws CalculatorException {
		testCalc.execute("m");
		assertTrue(testCalc.getMode());
	}
	
	@Test
	public void pushNegativeOnUnsignedMode() throws CalculatorException {
		Assertions.assertThrows(CalculatorException.class, () -> {
			testCalc.push(negativeOne);
    	});
	}
	
	@Test
	public void pushNegativeOnSignedMode() throws CalculatorException {
		testCalc.execute("m");
		testCalc.push(negativeOne);
		assertEquals(negativeOne, testCalc.peek(0));
	}
	
	@Test
	public void testBoundries() throws CalculatorException {
		testCalc.execute("m");
		testCalc.push("-128");
		testCalc.push("-1");
		testCalc.execute("+");
		assertEquals("127", testCalc.pop());
		
		testCalc.push("127");
		testCalc.push("1");
		testCalc.execute("+");
		assertEquals("-128", testCalc.pop());
	}
}
