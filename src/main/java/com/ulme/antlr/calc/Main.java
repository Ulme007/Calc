package com.ulme.antlr.calc;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        long result = MathEvaluator.evaluateExpression("3+42+5+6");
        System.out.println("Result: " + result);
    }
}
