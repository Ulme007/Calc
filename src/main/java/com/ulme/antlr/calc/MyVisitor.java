package com.ulme.antlr.calc;

public class MyVisitor extends CalcBaseVisitor<String> {
    @Override
    public String visitAddition(CalcParser.AdditionContext ctx) {
        visitChildren(ctx);
        if (ctx.getChildCount() == 1) {
            System.out.println(ctx.getChild(0));
        } else {
            System.out.println(ctx.getChild(2));
            System.out.println("addition");
        }
        return null;
    }
}
