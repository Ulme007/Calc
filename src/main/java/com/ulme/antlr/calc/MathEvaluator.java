package com.ulme.antlr.calc;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.PrintStream;

public class MathEvaluator {

    private PrintStream printStream;

    MathEvaluator(PrintStream printStream) {
        this.printStream = printStream;
    }

    Long evaluateFile(String filename) throws IOException {
        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);
        return evaluate(antlrInputStream);
    }

    Long evaluateExpression(String expression) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        return evaluate(antlrInputStream);
    }

    private Long evaluate(ANTLRInputStream antlrInputStream) {
        CalcLexer calcLexer = new CalcLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(calcLexer);
        CalcParser calcParser = new CalcParser(commonTokenStream);

        ParseTree parseTree = calcParser.programm();
        return new MyVisitor(printStream).visit(parseTree);
    }
}
