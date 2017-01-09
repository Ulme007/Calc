package com.ulme.antlr.calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MathEvaluatorTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Primitive value", "1", 1},
                {"Chain calculation", "3+42+5+6", 56},
        });
    }

    private String description;
    private String expression;
    private Long result;

    public MathEvaluatorTest(String description, String expression, long result) {
        this.description = description;
        this.expression = expression;
        this.result = result;
    }

    @Test
    public void test() throws Exception {
        assertEquals(description, result, MathEvaluator.evaluateExpression(expression));
    }
}