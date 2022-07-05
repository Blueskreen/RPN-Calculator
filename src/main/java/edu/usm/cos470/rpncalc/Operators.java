package edu.usm.cos470.rpncalc;

import edu.usm.cos470.rpncalc.exceptions.CalculatorException;
import edu.usm.cos470.rpncalc.exceptions.InvalidWordSize;
import edu.usm.cos470.rpncalc.exceptions.OutOfOperands;

import java.util.*;
import java.util.function.LongBinaryOperator;

public class Operators {

    Map<String, OperationInterface> mapBinary;
    List<String> numericalOperators;
    
    public Operators(Calculator calculator) {
        mapBinary = new HashMap<String, OperationInterface>();
        numericalOperators = new ArrayList<>();
        numericalOperators.addAll(Arrays.asList("+","-","*","/","%","&","|","^",">>","<<","==","!=","<",">",">=","<="));
        //arithmetic
        mapBinary.put("+", (long operandA, long operandB) -> operandA+operandB);
        mapBinary.put("-", (long operandA, long operandB) -> operandB-operandA);
        mapBinary.put("*", (long operandA, long operandB) -> operandA*operandB);
        mapBinary.put("/", (long operandA, long operandB) -> {
            if (operandA == 0) {
                throw new IllegalArgumentException("Cannot divide by zero");
            }
            return operandB / operandA;
        });
        mapBinary.put("%", (long operandA, long operandB) -> operandB%operandA);
        //bitwise operations except complement
        mapBinary.put("&", (long operandA, long operandB) -> operandA&operandB);
        mapBinary.put("|", (long operandA, long operandB) -> operandA|operandB);
        mapBinary.put("^", (long operandA, long operandB) -> operandA^operandB);
        mapBinary.put(">>", (long operandA, long operandB) -> operandB>>operandA);
        mapBinary.put("<<", (long operandA, long operandB) -> operandB<<operandA);
        //relational
        mapBinary.put("==", (long operandA, long operandB) -> {
            if(operandA == operandB)
                return 1;
            else
                return 0;
        });
        mapBinary.put("!=", (long operandA, long operandB) -> {
            if(operandA!=operandB)
                return 1;
            else
                return 0;
        });
        mapBinary.put(">", (long operandA, long operandB) -> {
            if(operandB>operandA)
                return 1;
            else
                return 0;
        });
        mapBinary.put("<", (long operandA, long operandB) -> {
            if(operandB<operandA)
                return 1;
            else
                return 0;
        });
        mapBinary.put(">=", (long operandA, long operandB) -> {
            if(operandB>=operandA)
                return 1;
            else
                return 0;
        });
        mapBinary.put("<=", (long operandA, long operandB) -> {
            if(operandB<=operandA)
                return 1;
            else
                return 0;
        });
        mapBinary.put("r", (long dummy, long dummy2)  ->{
            if(calculator.size() < 2) {
                throw new OutOfOperands("stack not large enough for reverse");
            }
            calculator.reverseTopTwo();
            return 0;
        });
        mapBinary.put("c", (long dummy, long dummy2) -> {
           calculator.clear();
           return 0;
        });
        mapBinary.put("s", (long dummy, long dummy2) -> {
            try {
                calculator.pushSize();
            } catch (CalculatorException e) { }
            return 0;
        });
        mapBinary.put("d", (long dummy, long dummy2) ->{
            if(calculator.size() < 1){
                throw new OutOfOperands("No operand to duplicate");
            }
            try {
                calculator.duplicateTop();
            } catch (CalculatorException e) { }
            return 0;
        });
        mapBinary.put("m", (long dummy, long dummy2) -> {
            try {
                calculator.changeMode();
            } catch (InvalidWordSize invalidWordSize) {}
            return 0;
        });
    }

    public boolean contains(String operator){
        return mapBinary.containsKey(operator);
    }
    public boolean isNumerical(String operator){
        return numericalOperators.contains(operator);
    }
}
