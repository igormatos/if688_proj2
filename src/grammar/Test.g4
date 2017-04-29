grammar Test;

@header{
  package antlr;
}

goal: (TEXT|NUMB)* EOF;
TEXT: [a-zA-Z]+;
NUMB: [0-9]+;
WS: [ \n\t\r]+ -> skip;
