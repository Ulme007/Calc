package com.ulme.antlr.calc;

import java.io.PrintStream;

public class MyVisitor extends CalcBaseVisitor<Long> {

    private PrintStream out;

    public MyVisitor(PrintStream out) {
        super();
        this.out = out;
    }

    @Override
    public Long visitPrintln(CalcParser.PrintlnContext ctx) {
        Long result = visit(ctx.argument);
        out.println(result);
        return null;
    }

    @Override
    public Long visitPlus(CalcParser.PlusContext ctx) {
        String right = ctx.right.getText();
        Long aLong = visitChildren(ctx);
        return aLong +
                Long.parseLong(right);
    }

    @Override
    public Long visitMinus(CalcParser.MinusContext ctx) {
        return visitChildren(ctx) -
                Long.parseLong(ctx.right.getText());
    }

    @Override
    public Long visitDiv(CalcParser.DivContext ctx) {
        return visitChildren(ctx) /
                Long.parseLong(ctx.right.getText());
    }

    @Override
    public Long visitMult(CalcParser.MultContext ctx) {
        return visitChildren(ctx) *
                Long.parseLong(ctx.right.getText());
    }

    @Override
    public Long visitNumber(CalcParser.NumberContext ctx) {
        return Long.parseLong(ctx.number.getText());
    }

    @Override
    protected Long aggregateResult(Long aggregate, Long nextResult) {
        if (aggregate == null) {
            return nextResult;
        }
        if (nextResult == null) {
            return aggregate;
        }
        return aggregate + nextResult;
    }
}
