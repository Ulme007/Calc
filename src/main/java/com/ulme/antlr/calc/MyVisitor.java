package com.ulme.antlr.calc;

import java.io.PrintStream;

public class MyVisitor extends CalcBaseVisitor<Long> {

    private PrintStream out;

    MyVisitor(PrintStream out) {
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
        Long left = visit(ctx.left);
        Long right = visit(ctx.right);
        return left + right;
    }

    @Override
    public Long visitMinus(CalcParser.MinusContext ctx) {
        Long left = visit(ctx.left);
        Long right = visit(ctx.right);
        return left - right;
    }

    @Override
    public Long visitDiv(CalcParser.DivContext ctx) {
        Long left = visit(ctx.left);
        Long right = visit(ctx.right);
        return left / right;
    }

    @Override
    public Long visitMult(CalcParser.MultContext ctx) {
        Long left = visit(ctx.left);
        Long right = visit(ctx.right);
        return left * right;
    }

    @Override
    public Long visitNumber(CalcParser.NumberContext ctx) {
        return Long.parseLong(ctx.number.getText());
    }
}
