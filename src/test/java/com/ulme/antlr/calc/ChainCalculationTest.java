package com.ulme.antlr.calc;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ChainCalculationTest {

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String expression =
                "if (1) {\n" +
                "    println(81);\n" +
                "} else {\n" +
                "    println(42);\n" +
                "}";

        String result = "81" + System.lineSeparator();

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression(expression);
        }

        assertEquals(result, byteArrayOutputStream.toString());
    }
}
