package edu.usm.cos470.rpncalc;
import edu.usm.cos470.rpncalc.exceptions.CalculatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VariableTests {

    @Test
    public void assignTest() throws CalculatorException{
        Calculator testCalc = new Calculator(8);
        testCalc.push("4");
        testCalc.execute("->x");
        testCalc.push("5");
        testCalc.push("x");
        testCalc.execute("+");
        Assertions.assertEquals(testCalc.pop(), "9");
        testCalc.push("10");
        testCalc.push("x");
        testCalc.execute("*");
        Assertions.assertEquals(testCalc.pop(), "40");
    }
}
