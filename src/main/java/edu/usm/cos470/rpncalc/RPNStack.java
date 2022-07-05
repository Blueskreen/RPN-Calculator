package edu.usm.cos470.rpncalc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import edu.usm.cos470.rpncalc.exceptions.OutOfOperands;

public class RPNStack<T> {
	
	private Stack<T> stack;
	
	public RPNStack() {
		stack = new Stack<T>();
	}
	
	public void push(T element) {
		stack.push(element);
	}
	
	public T pop() throws OutOfOperands {
		if(size() < 1) {
			throw new OutOfOperands("Nothing on the stack to pop");
		}
		return stack.pop();
	}
	
	public List<T> pop2() throws OutOfOperands {
		if(size() < 2) {
			throw new OutOfOperands("Not enough elements to pop two. Size: "+size());
		}
		List<T> result = new ArrayList<T>();
		result.add(pop());
		result.add(pop());
		return result;
	}
	
	public T peek(int location) throws OutOfOperands {
		int peekIndex = (stack.size() -1) - location;
    	if(location > stack.size() -1) {
    		throw new OutOfOperands(String.format("The item at location %d cannot be accessed. The stack only has %d elements", location, peekIndex));
    	}
    	return stack.elementAt(peekIndex);
	}
	
	public void clear() {
		stack = new Stack<T>();
	}
	
	public int size() {
		return stack.size();
	}
}
