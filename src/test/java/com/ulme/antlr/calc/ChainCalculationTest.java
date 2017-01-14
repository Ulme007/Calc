package com.ulme.antlr.calc;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ChainCalculationTest {

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String expression = "int primitive() {\n" +
                "    int i;\n" +
                "    i = 4;\n" +
                "    return i;\n" +
                "}\n" +
                "int i;\n" +
                "i = 42;\n" +
                "println(primitive());\n" +
                "println(i);";

        String result = "4" + System.lineSeparator() + "42" + System.lineSeparator();

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression(expression);
        }

        assertEquals(result, byteArrayOutputStream.toString());
    }
}
