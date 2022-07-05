package edu.usm.cos470.rpncalc;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;
import edu.usm.cos470.rpncalc.exceptions.InvalidStackNumberValue;
import edu.usm.cos470.rpncalc.exceptions.InvalidWordSize;

public class StackNumber {
	
	private static short wordSize = 0;
	private static long wordSizeMask = 0;
	private static boolean signed = false;
	
	private long value;
	private boolean isNegative = false;
	
	public StackNumber(int wordSize, long value) throws CalculatorException {
		updateWordSize(wordSize);
		this.value = andWithMask(value);
		checkValue();
	}
	
	public StackNumber(long value, boolean isNegative) throws CalculatorException {
		checkWordSize();
		this.isNegative = isNegative;
		this.value = andWithMask(value);
		checkValue();
	}
	
	public StackNumber(long value) throws CalculatorException {
		checkWordSize();
		this.value = andWithMask(value);
		checkValue();
	}
	
	public StackNumber() {
		this.value = 0l;
	}
	
	private static void checkWordSize() throws InvalidWordSize {
		if(wordSize <= 0)
			throw new InvalidWordSize("Expected wordsize to be a value > 0, but got"+wordSize);
	}
	
	private static void updateWordSize(int wordSize) {
		StackNumber.wordSize = (short) wordSize;
		updateWordSizeMask();
	}
	
	private static void updateWordSizeMask() {
		if(wordSize == 64) {
			wordSizeMask = Long.MAX_VALUE;
		}
		else {
			wordSizeMask = 0b1l; 
			for(int i = 0; i < wordSize; i++) {
				wordSizeMask <<= 1;
			}
			wordSizeMask -= 1;
		}
	}
	
	public String toString() {
		String result = (isNegative ? "-" : "");
		return result + Long.toString(value);
	}
	public static int getWordSize() {
		return wordSize;
	}
	public static void setWordSize(int wordSize) throws InvalidWordSize {
		updateWordSize(wordSize);
		checkWordSize();
	}
	public static long getWordSizeMask() {
		return wordSizeMask;
	}
	public long getValue() throws InvalidStackNumberValue {
		checkValue();
		return isNegative ? value * -1L : value;
	}
	
	private void checkValue() throws InvalidStackNumberValue {
		if(value < 0)
			throw new InvalidStackNumberValue("StackNumber should never hold a negative number: "+value);
		
		value = andWithMask(value);
		if(!signed && isNegative)
			isNegative = false;
	}

	public void setValue(long value, boolean isNegative) throws InvalidStackNumberValue {
		this.value = andWithMask(value);
		this.isNegative = isNegative;
		checkValue();
	}
	
	public boolean getIsNegative() {
		return isNegative;
	}
	public void setIsNegative(boolean isNegative) throws InvalidStackNumberValue {
		this.isNegative = isNegative;
		checkValue();
	}
	
	private long andWithMask(long rawValue) {
		if(signed) {
			if(isNegative && rawValue > wordSizeMask+1) {
				isNegative = false;
				return wordSizeMask;
			}
			else if(isNegative && rawValue == wordSizeMask+1) {
				return wordSizeMask+1;
			}
			else if(!isNegative && rawValue > wordSizeMask) {
				isNegative = true;
				return wordSizeMask+1;
			}
		}
		return rawValue & wordSizeMask;
	}

	public static boolean isSigned() {
		return signed;
	}

	public static void setSigned(boolean signed) {
		StackNumber.signed = signed;
	}
	
	
}
