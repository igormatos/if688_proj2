grammar PT_IMSPC;

@header{
  package antlr;
}

goal: mainClass(classDeclaration)* EOF;

mainClass: 'class' identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' identifier ')' '{' statement '}' '}';

classDeclaration: 'class' identifier ('extends' identifier)? '{' (varDeclaration)* (methodDeclaration)* '}';

varDeclaration: formal ';';

methodDeclaration: 'public' formal '(' (formal (',' formal)*)? ')' '{' ( varDeclaration )* (statement)* 'return' expr ';' '}';

formal: type identifier;

type: 'int' '[' ']'
	| 'boolean'
	| 'int'
	| identifier;

statement: '{' statement* '}'
		 | 'if' '(' expr ')' statement 'else' statement
		 | 'while' '(' expr ')' statement
		 | 'System.out.println' '(' expr ')' ';'
		 | identifier '=' expr ';'
		 | identifier '[' expr ']' '=' expr ';';


expr: expr op=('&&'|'<'|'+'|'-'|'*') expr
	| expr '[' expr ']'
	| expr '.' 'length'
	| expr '.' identifier '(' (expr (',' expr)*)? ')'
	| INTEGER_LITERAL
	| 'true'
	| 'false'
	| identifier
	| 'this'
	| 'new''int''['expr']'
	| 'new' identifier '('')'
	| '!' expr
	| '(' expr ')';

identifier: IDENTIFIER;
INTEGER_LITERAL: [0] | ([1-9]([0-9])*);
IDENTIFIER: ('_' | [A-Za-z])('_'|([A-Za-z] | INTEGER_LITERAL))*;
SPACES: [ \r\n\t] ->skip;
COMMENT_LINE: '//'(~[\n\r])* ->skip;
COMMENT_BLOCK: ('/*'(.)*?'*/') ->skip;
