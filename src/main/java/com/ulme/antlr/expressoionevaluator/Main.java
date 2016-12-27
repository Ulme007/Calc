package com.ulme.antlr.expressoionevaluator;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        String filename = Main.class.getClassLoader().getResource("code.demo").getPath();
//        ANTLRInputStream antlrInputStream = new ANTLRFileStream(filename);

        ANTLRInputStream antlrInputStream = new ANTLRInputStream("3+42+5+6");
        DemoLexer demoLexer = new DemoLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(demoLexer);
        DemoParser demoParser = new DemoParser(commonTokenStream);

        ParseTree parseTree = demoParser.addition();
        new MyVisitor().visit(parseTree);
    }
}
