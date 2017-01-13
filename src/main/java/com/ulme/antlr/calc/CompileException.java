package com.ulme.antlr.calc;

import org.antlr.v4.runtime.Token;

public class CompileException extends RuntimeException {

    private final int line;
    private final int column;

    public CompileException(Token token) {
        line = token.getLine();
        column = token.getCharPositionInLine();
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
