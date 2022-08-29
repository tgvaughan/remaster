grammar ConditionGrammar;

expression :
    '(' expression ')'                                          # Bracketed
    |   op=(SUM|MIN|MAX) '(' expression ')'                     # UnaryOp
    |   expression op=('=='|'!='|'<'|'>'|'<='|'>=') expression  # Equality
    |   expression op=('&&'|'||') expression                    # BooleanOp
    |   popname loc?                                            # Pop
    |   val=('0' | NZINT | NNFLOAT )                            # Number
    ;

// Misc parser rules:

popname : IDENT ;
loc : '[' locidx ']' ;
locidx : '0' | NZINT | IDENT ;

// Lexer rules:

SUM : 'sum';
MIN : 'min';
MAX : 'max';

AND : '&&' ;
OR : '||' ;

EQ: '==';
GT: '>';
LT: '<';
GE: '>=';
LE: '<=';
NE: '!=';

ZERO : '0' ;
NZINT : NZD D* ;
NNFLOAT : ('0' | NZINT) ('.' D*) ([eE] '-'? D+)? ;
fragment D : [0-9] ;
fragment NZD : [1-9] ;

IDENT : [a-zA-Z_][a-zA-Z_0-9]* ;

COMMENT_SINGLELINE: '//' .*? '\n' -> skip ;
COMMENT_MULTILINE: '/*' .*? '*/' -> skip ;

WHITESPACE : [ \t\r\n]+ -> skip ;
