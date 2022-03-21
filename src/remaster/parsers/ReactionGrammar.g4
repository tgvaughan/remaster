grammar ReactionGrammar;

// Reaction string parser rules:

reaction : reactants '->' products EOF;
reactants : popsum ;
products : popsum ;
popsum : popel ('+' popel)* | '0' ;
popel : factor? popname loc? (':' id)?;
factor : NZINT ;
id: '0' | NZINT | IDENT;

// Misc parser rules:

popname : IDENT ;
loc : '[' locidx ']' ;
locidx : '0' | NZINT | IDENT ;

// Lexer rules:

ZERO : '0' ;
NZINT : NZD D* ;
NNFLOAT : ('0' | NZINT) ('.' D*) ([eE] '-'? D+)? ;
fragment D : [0-9] ;
fragment NZD : [1-9] ;

IDENT : [a-zA-Z_][a-zA-Z_0-9]* ;

COMMENT_SINGLELINE: '//' .*? '\n' -> skip ;
COMMENT_MULTILINE: '/*' .*? '*/' -> skip ;

WHITESPACE : [ \t\r\n]+ -> skip ;
