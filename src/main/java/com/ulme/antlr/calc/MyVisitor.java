package com.ulme.antlr.calc;

import org.antlr.v4.runtime.Token;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyVisitor extends CalcBaseVisitor<Long> {

    private PrintStream out;
    private Map<String, Long> variables = new HashMap<>();
    private Map<String, CalcParser.FunctionDefinitionContext> functions = new HashMap<>();

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
    public Long visitFunctionCall(CalcParser.FunctionCallContext ctx) {
        CalcParser.FunctionDefinitionContext functionDefinitionContext = functions.get(ctx.funcName.getText());

        //save global variables map
        Map<String, Long> oldVariables = this.variables;

        // create a local variables map
        variables = new HashMap<>();

        List<CalcParser.ExpressionContext> expressions = ctx.arguments.expressions;
        List<CalcParser.VarDeclarationContext> declarations = functionDefinitionContext.params.declarations;
        for (int i = 0; i < declarations.size(); i++) {
            CalcParser.VarDeclarationContext varDeclarationContext = declarations.get(i);
            String variableName = varDeclarationContext.varName.getText();
            Long value = visit(expressions.get(i));
            variables.put(variableName, value);
        }

        visit(functionDefinitionContext.statements);
        Long result = visit(functionDefinitionContext.returnValue);

        // set back global variables map
        variables = oldVariables;

        return result;
    }

    @Override
    public Long visitFunctionDefinition(CalcParser.FunctionDefinitionContext ctx) {
        functions.put(ctx.funcName.getText(), ctx);
        return null;
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
