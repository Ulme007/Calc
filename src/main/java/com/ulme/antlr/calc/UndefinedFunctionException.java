package com.ulme.antlr.calc;

import org.antlr.v4.runtime.Token;

public class UndefinedFunctionException extends CompileException {

    private String functionName;
    public UndefinedFunctionException(Token functionNameToken) {
        super(functionNameToken);
        functionName = functionNameToken.getText();
    }


    @Override
    public String getMessage() {
        return getLine() + ":" + getColumn() + " call to undefined function: <" + functionName + ">";
    }
}
