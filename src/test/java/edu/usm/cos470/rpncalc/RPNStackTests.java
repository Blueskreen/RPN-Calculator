package edu.usm.cos470.rpncalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;
import edu.usm.cos470.rpncalc.exceptions.OutOfOperands;

public class RPNStackTests {
	
	RPNStack<Long> stack;
	long testValue = 7L;
	
	@BeforeEach
	public void setup() {
		stack = new RPNStack<Long>();
	}
	
	@Test
	public void testPushPop() throws OutOfOperands {
		stack.push(testValue);
		assertEquals(testValue, stack.pop());
	}
	
	@Test
	public void testPopErrors() {
		Assertions.assertThrows(OutOfOperands.class, () -> {
			stack.pop();
    	});
	}
	
	@Test
	public void testPop2() throws OutOfOperands {
		List<Long> espectedValues = fillStack(2);
		List<Long> values = stack.pop2();
		assertEquals(espectedValues.get(0), values.get(1));
		assertEquals(espectedValues.get(1), values.get(0));
	}
	
	@Test
	public void testPeek() throws OutOfOperands {
		int numToAdd = 3;
		List<Long> espectedValues = fillStack(numToAdd);
		Long value = stack.peek(0);
		assertEquals(espectedValues.get(numToAdd-1), value);
	}
	
	@Test
	public void testPeekErrors() {
		Assertions.assertThrows(OutOfOperands.class, () -> {
			stack.peek(0);
    	});
	}
	
	@Test
	public void testSize() {
		assertEquals(0, stack.size());
		fillStack(100);
		assertEquals(100, stack.size());
	}
	
	@Test
	public void testClear() {
		fillStack(100);
		stack.clear();
		assertEquals(0, stack.size());
	}
	
	private List<Long> fillStack(int numberOfValues){
		List<Long> result = new ArrayList<Long>();
		Random numberGenerator = new Random(Integer.MAX_VALUE);
		int newValue;
		for(int i = 0; i < numberOfValues; i++) {
			newValue = numberGenerator.nextInt();
			result.add((long) newValue);
			stack.push((long) newValue);
		}
		return result;
	}
	
}
