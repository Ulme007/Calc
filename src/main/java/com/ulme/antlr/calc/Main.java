package com.ulme.antlr.calc;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MathEvaluator mathEvaluator = new MathEvaluator(System.out);
        long result = mathEvaluator.evaluateExpression("3+42+5+6");
        System.out.println("Result: " + result);
    }
}
