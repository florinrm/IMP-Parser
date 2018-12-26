
import java.util.*;

%%

%class Lexer
%line
%int
%{
    Stack<Expression> stack = new Stack<>();
    // list of variables from program, in order to check what of them are initialized
    ArrayList<VarNode> variables = new ArrayList<>();

    public static final String mainNode = "int";
    public static final String assignSign = "=";
    public static final String plusSign = "+";
    public static final String divSign = "/";

    private boolean checkInstance() {
        return stack.peek() instanceof Symbol || stack.peek() instanceof VarNode
            || stack.peek() instanceof IntNode || stack.peek() instanceof PlusNode
            || stack.peek() instanceof DivNode || stack.peek() instanceof BracketNode;
    }

    public Expression build() {
        Expression expr = null;
        while (checkInstance()) {
                if (stack.peek() instanceof PlusNode) {
                    PlusNode node = (PlusNode) expr;
                    node.setFirstChild(stack.pop());
                    expr = node;
                } else if (stack.peek() instanceof Symbol) {
                    Symbol symbol = (Symbol) stack.peek();
                    if (symbol.getSymbol().compareTo(mainNode) == 0)
                        return stack.peek();
                    else if (symbol.getSymbol().compareTo(assignSign) == 0) { //=
                        stack.pop();
                        expr = new AssignmentNode(stack.peek(), expr);
                        stack.pop();
                        if (stack.peek() instanceof SequenceNode)
                            return expr;
                        else if (!(stack.peek() instanceof MainNode)) {
                            stack.push(new SequenceNode(stack.peek()));
                            stack.pop();
                            return expr;
                        } else {
                            System.out.println("pula");
                            return new SequenceNode(expr);
                        }
                    } else if (symbol.getSymbol().compareTo(plusSign) == 0) { //+
                        stack.pop();
                        if (expr instanceof PlusNode) {
                            PlusNode node = (PlusNode) expr;
                            PlusNode temp = new PlusNode(node.getFirstChild());
                            node.setFirstChild(temp);
                            expr = node;
                        } else {
                            expr = new PlusNode(expr);
                        }
                    }
                } else {
                    expr = stack.pop();
                }
            }
        return expr;
    }
%}

number = [1-9][0-9]* | 0
string = [a-z]+
var = {string}
aVal = {number}
bVal = "true" | "false"
add = "+"
div = "\\"
and = "&&"
greater = ">"
assign = "="
type = "int"
close_instr = ";"
ignore_expr = "\n" | "," | " "
open_bracket = "{"
close_bracket = "}"
open_parenthesis = "("
close_parenthesis = ")"

%%

{type} {
    stack.push(new Symbol(mainNode));
}
{var} {
    if (stack.peek() instanceof Symbol) {
            Symbol symbol_stack = (Symbol) stack.peek();
            if (symbol_stack.getSymbol().compareTo(mainNode) == 0) {
                variables.add(new VarNode(yytext()));
            } else if (symbol_stack.getSymbol().compareTo(divSign) == 0) {
                stack.pop();
                stack.push(new DivNode(stack.peek(), new VarNode(yytext())));
                stack.pop();
            } else {
                stack.push(new VarNode(yytext()));
            }
    } else {
        stack.push(new VarNode(yytext()));
    }
}
{aVal} {
    if (stack.peek() instanceof Symbol) {
        Symbol symbol = (Symbol) stack.peek();
        if (symbol.getSymbol().compareTo(divSign) == 0) {
            stack.pop();
            stack.push(new DivNode(stack.peek(), new IntNode(yytext())));
            stack.pop();
        } else {
            stack.push(new IntNode(yytext()));
        }
    } else {
        stack.push(new IntNode(yytext()));
    }    
}
{bVal} {stack.push(new BoolNode(yytext()));}
{assign} {stack.push(new Symbol(yytext()));}
{add} {stack.push(new Symbol(yytext()));}
{div} {stack.push(new Symbol(yytext()));}
{and} {stack.push(new Symbol(yytext()));}
{greater} {stack.push(new Symbol(yytext()));}
{close_instr} {
    Expression expr = build();
    if (!(expr instanceof Symbol)) {
        stack.push(expr);
    } else {
        Symbol symbol = (Symbol) expr;
        if (symbol.getSymbol().compareTo(mainNode) != 0) {
            stack.push(expr);
        } else {
            stack.pop();
            stack.push(new MainNode (null));
        }
    }
}
{ignore_expr} {/* do nothing */}
. {}