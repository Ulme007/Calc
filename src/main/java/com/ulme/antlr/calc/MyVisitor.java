package com.ulme.antlr.calc;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class MyVisitor extends CalcBaseVisitor<Long> {

    private PrintStream out;
    private Map<String, Long> variables = new HashMap<>();

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

    @Override
    public Long visitVarDeclaration(CalcParser.VarDeclarationContext ctx) {
        String varName = ctx.varName.getText();
        if (variables.containsKey(varName)) {
            throw new VariableAlreadyDefinedException(ctx.varName);
        }
        variables.put(varName, null);
        return null;
    }

    @Override
    public Long visitAssignment(CalcParser.AssignmentContext ctx) {
        Long value = visit(ctx.expr);
        String varName = ctx.varName.getText();
        if (!variables.containsKey(varName)) {
            throw new UndeclaredVariableException(ctx.varName);
        }
        variables.put(varName, value);
        return value;
    }

    @Override
    public Long visitVariable(CalcParser.VariableContext ctx) {
        String varName = ctx.varName.getText();
        Long value = variables.get(varName);
        if (value == null) {
            throw new UndeclaredVariableException(ctx.varName);
        }
        return value;
    }
}
