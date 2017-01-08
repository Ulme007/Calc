package com.ulme.antlr.calc;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class MathEvaluator {

    static void evaluateFile(String filename) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);
        evaluate(antlrInputStream);
    }

    static void evaluateExpression(String expression) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        evaluate(antlrInputStream);
    }

    private static void evaluate(ANTLRInputStream antlrInputStream) {
        CalcLexer calcLexer = new CalcLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(calcLexer);
        CalcParser calcParser = new CalcParser(commonTokenStream);

        ParseTree parseTree = calcParser.addition();
        new MyVisitor().visit(parseTree);
    }
}
