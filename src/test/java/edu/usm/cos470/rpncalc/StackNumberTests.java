package edu.usm.cos470.rpncalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;

public class StackNumberTests {
	
	StackNumber num;
	
	
	
	@BeforeEach
	public void setup() throws CalculatorException {
		num = new StackNumber(8, 120L);
	}
	
	@Test
	public void testBitMask() {
		long expected = 0b11111111,
				result = StackNumber.getWordSizeMask();
		assertEquals(expected, result);
	}
	@Test
	public void testOversizedInput() throws CalculatorException {
		num = new StackNumber(256L);
		long expected = 0;
		assertEquals(expected, num.getValue());
	}
	@Test
	public void testDefaultConstructor() throws CalculatorException {
		num = new StackNumber();
		assertEquals(0, num.getValue());
	}
	@Test 
	public void testWordSizeConstructor() throws CalculatorException {
		num = new StackNumber(32, Integer.MAX_VALUE);
		int wordSize = StackNumber.getWordSize();
		assertEquals(32, wordSize);
		assertEquals(Integer.MAX_VALUE, num.getValue());
	}
	@Test
	public void testSetValue() throws CalculatorException {
		num.setValue(127, false);
		assertEquals(127, num.getValue());
	}
	@Test
	public void testSetValueOutOfBounds() throws CalculatorException {
		num.setValue(256, false);
		assertEquals(0, num.getValue());
	}
	@Test
	public void testToString() {
		assertEquals("120", num.toString());
	}
	@Test
	public void testSetWordSize() throws CalculatorException {
		StackNumber.setWordSize(4);
		assertEquals(4, StackNumber.getWordSize());
		assertEquals(0b1111, StackNumber.getWordSizeMask());
	}
	@Test
	public void testValuePostSetWordSize() throws CalculatorException {
		num = new StackNumber(255);
		StackNumber.setWordSize(4);
		assertEquals(15, num.getValue());
	}
}
