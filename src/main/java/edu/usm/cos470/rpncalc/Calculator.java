package edu.usm.cos470.rpncalc;
import java.util.List;
import java.util.Set;

import edu.usm.cos470.rpncalc.exceptions.*;

public class Calculator {
    RPNStack<StackNumber> stack;
    int wordSize;
    Operators operators;
    Variables variables;
    boolean overflowFlag;
    boolean signedMode = false;

    public Calculator(int wordSize) throws CalculatorException{
        if(wordSize>64 || wordSize < 4){
            throw new IllegalArgumentException("Word size must be between 4 and 64");
        }
        stack = new RPNStack<StackNumber>();
        StackNumber.setWordSize(wordSize);
        this.wordSize = wordSize;
        variables = new Variables();
        overflowFlag = false;
        operators = new Operators(this);
    }

    public void push(String inputString) throws CalculatorException{
        Long input;
        if(variables.checkAssignment(inputString)){
            input = variables.get(inputString);
        } else {
            input = Long.valueOf(inputString);
        }
        boolean isNegative = (input < 0);
        checkMode(isNegative);
        input = (isNegative ? input * -1L : input);
        stack.push(new StackNumber(input, isNegative));
    }

    private void checkMode(boolean isNegative) throws CalculatorException {
		if(isNegative && !signedMode)
			throw new CalculatorException("Cannot push a singed value on the stack when not in sighend mode.");
	}

	public String pop() throws OutOfOperands{
        return stack.pop().toString();
    }
	
    public String peek(int location) throws OutOfOperands{
        return stack.peek(location).toString();
    }

    public void clear(){
        stack = new RPNStack<StackNumber>();
        variables = new Variables();
    }
    
    public int size() {
    	return stack.size();
    }

    public void execute(String operator) throws CalculatorException{
        if (isValidOperator(operator)){
            if(operators.isNumerical(operator)){
                executeNumericalOperation(operator);
            } else if (operator.length() >= 3 && operator.substring(0,2).equals("->")){
                assignVariable(operator.substring(2,operator.length()));
            } else if (operators.contains(operator)){
                executeNonNumerical(operator);
            }
        } else {
            throw new InvalidOperator(String.format("%s is not a valid operator", operator));
        }
    }

    private boolean isValidOperator(String operator) {
        if (operators.contains(operator)){
            return true;
        } else if (operator.length() >= 3 && operator.substring(0,2).equals("->")){
            return true;
        } else{
            return false;
        }
    }

    public void changeMode() throws InvalidWordSize {
		signedMode = !signedMode;
		if(signedMode)
			StackNumber.setWordSize(StackNumber.getWordSize() - 1);
		else
			StackNumber.setWordSize(StackNumber.getWordSize() + 1);
		
		StackNumber.setSigned(signedMode);
	}

	private void executeNumericalOperation(String operator) throws CalculatorException {
		if(stack.size() < 2)
			throw new OutOfOperands("Not enough operands on the stack to execute reverse");
		List<StackNumber> topTwo = stack.pop2();
        long result = operators.mapBinary.get(operator).applyAsLong(
        		topTwo.remove(0).getValue(), topTwo.remove(0).getValue());
        checkForOverflow(result);
        push(Long.toString(result));
	}

	private void executeNonNumerical(String operator) throws CalculatorException {
        operators.mapBinary.get(operator).applyAsLong(0,0);
    }

    private void assignVariable(String var) throws CalculatorException {
        if (stack.size() < 1){
            throw new CalculatorException("No value on stack to assign");
        }
        if (var.length() < 1){
            throw new VariableNotFound("No variable provided for assignment");
        }
        long value = stack.pop().getValue();
        variables.assign(var, value);
    }
	
	public void reverseTopTwo() throws OutOfOperands {
		List<StackNumber> topTwo = stack.pop2();
		stack.push(topTwo.remove(0));
		stack.push(topTwo.remove(0));
	}
	
	public void duplicateTop() throws CalculatorException {
		String topValue = peek(0);
		push(topValue);
	}

	public void pushSize() throws CalculatorException {
		push(Integer.valueOf(stack.size()).toString());
	}
	
	private void checkForOverflow(long value) {
		if(value > StackNumber.getWordSizeMask())
			overflowFlag = true;
		else
			overflowFlag = false;
	}

	public String flags(){
        if(overflowFlag){
            return "O";
        }
        else{
            return "o";
        }
    }
	
	public boolean getMode() {
		return signedMode;
	}
}
