import java.util.*;

%%

%class Lexer
%line
%{

}%

digit = [1-9]*
number = {digit}(0 | {digit})* | 0
string = [a-z]+
var = {string}
aVal = {number}
bVal = True | False