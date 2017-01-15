package com.ulme.antlr.calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MathEvaluatorTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws IOException {
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

                example("function/without_parameter",
                        "4" + System.lineSeparator()),

                example("function/scopes",
                        "4" + System.lineSeparator() + "42" + System.lineSeparator()),

                example("function/with_parameter",
                        "13" + System.lineSeparator()),

                example("function/overloading",
                        "1" + System.lineSeparator() + "2" + System.lineSeparator()),

                example("branch/if_int_false",
                        "42" + System.lineSeparator()),

                example("branch/if_int_true",
                        "81" + System.lineSeparator()),

                {"lower than true", "println(1 < 2);", "1" + System.lineSeparator()},
                {"lower than false", "println(2 < 2);", "0" + System.lineSeparator()},

                {"lower or equal than true", "println(2 <= 2);", "1" + System.lineSeparator()},
                {"lower or equal than false", "println(3 <= 2);", "0" + System.lineSeparator()},

                {"greater than true", "println(2 > 1);", "1" + System.lineSeparator()},
                {"greater than false", "println(2 > 2);", "0" + System.lineSeparator()},

                {"greater or equal than true", "println(2 >= 2);", "1" + System.lineSeparator()},
                {"greater or equal than false", "println(1 >= 2);", "0" + System.lineSeparator()},

                {"and true", "println(1 && 1);", "1" + System.lineSeparator()},
                {"and left false", "println(0 && 1);", "0" + System.lineSeparator()},
                {"and right false", "println(1 && 0);", "0" + System.lineSeparator()},
                {"and left and right false", "println(0 && 0);", "0" + System.lineSeparator()},
                example("operators/and_skip_right",
                        "0" + System.lineSeparator() + "0" + System.lineSeparator()),

                {"or true", "println(1 || 1);", "1" + System.lineSeparator()},
                {"or left false", "println(0 || 1);", "1" + System.lineSeparator()},
                {"or right false", "println(1 || 0);", "1" + System.lineSeparator()},
                {"or left and right false", "println(0 || 0);", "0" + System.lineSeparator()},
                example("operators/or_skip_right",
                        "1" + System.lineSeparator() + "1" + System.lineSeparator()),
                {"print", "print(42);", "42"},
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

    private static String[] example(String name, String expectedResult) throws IOException {
        try (InputStream in = MathEvaluatorTest.class.getResourceAsStream("/examples/" + name + ".txt")) {
            if (in == null) {
                throw new IllegalArgumentException("example '" + name + "' not found");
            }
            String code = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
            return new String[]{name, code, expectedResult};
        }
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