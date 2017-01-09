package com.ulme.antlr.calc;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class MathEvaluator {

    static Long evaluateFile(String filename) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);
        return evaluate(antlrInputStream);
    }

    static Long evaluateExpression(String expression) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        return evaluate(antlrInputStream);
    }

    private static Long evaluate(ANTLRInputStream antlrInputStream) {
        CalcLexer calcLexer = new CalcLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(calcLexer);
        CalcParser calcParser = new CalcParser(commonTokenStream);

        ParseTree parseTree = calcParser.addition();
        return new MyVisitor().visit(parseTree);
    }
}
