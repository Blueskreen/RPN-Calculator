package edu.usm.cos470.rpncalc;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;

@FunctionalInterface
public interface OperationInterface<T, U, R> {
    long applyAsLong(long t, long u) throws CalculatorException;
}
