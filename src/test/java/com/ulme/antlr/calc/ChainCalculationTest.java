package com.ulme.antlr.calc;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ChainCalculationTest {

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String expression = "int add(int a, int b) {\n" +
                "    return a + b;\n" +
                "}\n" +
                "println(add(5,8));";

        String result = "13" + System.lineSeparator();

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression(expression);
        }

        assertEquals(result, byteArrayOutputStream.toString());
    }
}
