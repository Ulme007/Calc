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
                {"Add two values", "println(2+3*3);", "11" + System.lineSeparator()},
                {"Add two values", "println(9-2*3);", "3" + System.lineSeparator()},

                {"Use a variable", "int foo; foo = 42; println(foo);", "42" + System.lineSeparator()},
                {"Use a variable and add a value", "int foo; foo = 42; println(foo+2);", "44" + System.lineSeparator()},
                {"Add two variables", "int a; int b; a = 2; b = 3; println(a+b);", "5" + System.lineSeparator()},

                {"Call primitive function", "int randomNumber() { return 4; } println(randomNumber());", "4" + System.lineSeparator()},
                {"Call function with expression", "int primitive() {\n" +
                        "    int i;\n" +
                        "    i = 4;\n" +
                        "    return i;\n" +
                        "}\n" +
                        "println(primitive());", "4" + System.lineSeparator()},

                {"Global and local variables with same name","int primitive() {\n" +
                        "    int i;\n" +
                        "    i = 4;\n" +
                        "    return i;\n" +
                        "}\n" +
                        "int i;\n" +
                        "i = 42;\n" +
                        "println(primitive());\n" +
                        "println(i);", "4" + System.lineSeparator() + "42" + System.lineSeparator()},
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