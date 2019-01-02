// Define a grammar called Hello
grammar Hello;
/* Definim regulile gramaticii (cu litere mici) */
/*
main  : list ;  // Inceputul gramaticii, S -> list
list: '()' | '(' sequence ')' | cons | concat;
sequence : element | element ' ' sequence;
element
    :   integer
    |   cons
    |   concat
    |   list
    ;
cons : '(: ' integer ' ' list ')';
concat : '(++ ' left=list ' ' right=list ')';
integer : INTEGER;
*/

main : variablesList CLOSE_INSTR sequence ;
variablesList : TYPE (VAR IGNORE_EXPR)* VAR ;
if_stmt : STAT_IF b_expression block STAT_ELSE block ;
while_stmt : STAT_WHILE b_expression block ;

a_expression : bracket |
            a_expression div a_expression |
            a_expression plus a_expression |
            variable |
            integer ;

b_expression : bracket |
            not b_expression |
            a_expression greater a_expression |
            b_expression and b_expression |
            bool ;

sequence : statement statement | statement sequence ;
statement : assign | while_stmt | if_stmt ;

variable : VAR ;
integer : AVAL ;
plus : '+' ;
assign : variable ASSIGN a_expression CLOSE_INSTR ;
div : '/' ;
not : '!' ;
greater : '>' ;
and : '&&' ;
bool : BVAL ;
bracket : OPEN_PAR a_expression CLOSE_PAR |
        OPEN_PAR b_expression CLOSE_PAR ;
block : OPEN_BRACKET CLOSE_BRACKET |
        OPEN_BRACKET sequence CLOSE_BRACKET |
        OPEN_BRACKET statement CLOSE_BRACKET ;

/* Definim Tokenii ce pot aparea in timpul parsarii */

TYPE : 'int' ;
STAT_IF : 'if' ;
STAT_WHILE: 'while' ;
STAT_ELSE : 'else' ;
BVAL : 'true' | 'false' ;
VAR : [a-z]+ ;
AVAL : [0-9]+ ;
ADD : '+' ;
DIV : '/' ;
AND : '&&' ;
GREATER : '>' ;
ASSIGN : '=' ;
CLOSE_INSTR : ';' ;
TAB : '\t' -> skip ;
NEW_LINE : '\n' -> skip ;
SPACE : ' ' -> skip ;
IGNORE_EXPR : ',' ;
OPEN_BRACKET : '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PAR : '(' ;
CLOSE_PAR : ')' ;
NOT : '!' ;

/* Alte lucruri ce pot fi folositoare, sugerez sa le incercati */
/*
    O varianta de a declara reguli este prin adnotarea lor
    In acest moment nu vom mai avea un nod element in arbore.
    Prin aceasta metoda vom avea 4 tipuri diferite de noduri

    Poate fi util cand parsam reguli de genul AExp + AExp (in cazul in care ANTLR da erori)
    Mai multe detalii despre astfel de erori in anul 4 -> CPL
    Cele 4 'ramuri' vor fi tratate ca reguli

    element
    :   integer     #int
    |   cons        #colon
    |   concat      #plusplus
    |   list;       #l
*/

/*
    concat : '(++ ' list ' ' list ')';
    Cele 2 'list' vor fi accesate in cadrul contextului prin ctx.list(0) si ctx,list(1)

        Daca dorim sa redenumim elementele, pentru a nu ne incurca cu indicii,
    putem folosi etichete pentru acestea:

    concat : '(++ ' left=list ' ' right=list ')';
    Acum accesarea lor se va face prin ctx.left si ctx.right
    Aceasta functionalitate este mai mult de design, daca v-ar simplifica munca.
*/
