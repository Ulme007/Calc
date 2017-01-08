grammar Calc;

addition: addition '+' ZAHL
        | ZAHL;

ZAHL: [0-9]+;