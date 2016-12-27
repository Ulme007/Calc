package com.ulme.antlr.expressoionevaluator;

public class MyVisitor extends DemoBaseVisitor<String> {

    @Override
    public String visitAddition(DemoParser.AdditionContext ctx) {
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
