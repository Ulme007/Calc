package com.ulme.antlr.calc;

public class MyVisitor extends CalcBaseVisitor<Long> {

    @Override
    public Long visitPlus(CalcParser.PlusContext ctx) {
        return visitChildren(ctx) +
                Long.parseLong(ctx.rechts.getText());
    }

    @Override
    public Long visitZahl(CalcParser.ZahlContext ctx) {
        return Long.parseLong(ctx.zahl.getText());
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
