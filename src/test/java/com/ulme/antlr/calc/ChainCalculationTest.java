package com.ulme.antlr.calc;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ChainCalculationTest {

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression("println(1+2+4);");
        }

        //assertEquals(description, result, byteArrayOutputStream.toString());
    }
}
