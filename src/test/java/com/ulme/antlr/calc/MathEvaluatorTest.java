package com.ulme.antlr.calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MathEvaluatorTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Primitive value", "println(1);", "1" + System.lineSeparator()},
                {"Add two values", "println(1+2);", "3" + System.lineSeparator()},
                {"Add two values with white space", "println(1 + 2);", "3" + System.lineSeparator()},
                {"Chain calculation", "println(3+42+5+6);", "56" + System.lineSeparator()},
                {"Two println", "println(1); println(2);",
                        "1" + System.lineSeparator() +
                                "2" + System.lineSeparator()},
                {"Add two values", "println(3-2);", "1" + System.lineSeparator()},
                {"Add two values", "println(2*3);", "6" + System.lineSeparator()},
                {"Add two values", "println(6/2);", "3" + System.lineSeparator()},
                {"Add two values", "println(7/2);", "3" + System.lineSeparator()},
                {"Add two values", "println(8-2+5);", "11" + System.lineSeparator()},
                {"Add two values", "println(8/2*4);", "16" + System.lineSeparator()},
//                {"Add two values", "println(2+3*3)", "11" + System.lineSeparator()},
//                {"Add two values", "println(9-2*3)", "3" + System.lineSeparator()},
        });
    }

    private String description;
    private String expression;
    private String result;

    public MathEvaluatorTest(String description, String expression, String result) {
        this.description = description;
        this.expression = expression;
        this.result = result;
    }

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression(expression);
        }

        assertEquals(description, result, byteArrayOutputStream.toString());
    }
}