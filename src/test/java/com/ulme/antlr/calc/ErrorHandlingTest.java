package com.ulme.antlr.calc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ErrorHandlingTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void readingUndefinedVariableThrowsUndeclaredVariableException() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        thrown.expect(UndeclaredVariableException.class);
        thrown.expectMessage("1:8 undeclared variable <x>");

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression("println(x);");
        }
    }

    @Test
    public void writingUndefinedVariableThrowsUndeclaredVariableException() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        thrown.expect(UndeclaredVariableException.class);
        thrown.expectMessage("1:0 undeclared variable <x>");

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression("x = 5;");
        }
    }

    @Test
    public void defineVariableAlreadyDefinedThrowsVariableAlreadyDefinedException() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        thrown.expect(VariableAlreadyDefinedException.class);
        thrown.expectMessage("2:4 variable already defined: <x>");

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            MathEvaluator mathEvaluator = new MathEvaluator(printStream);
            mathEvaluator.evaluateExpression("int x;" + System.lineSeparator() +
                    "int x;");
        }
    }
}
