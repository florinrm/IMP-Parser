
import java.util.*;

%%

%class Lexer
%line
%int
%{
    Stack<Expression> stack = new Stack<>();
    // list of variables from program, in order to check what of them are initialized
    ArrayList<VarNode> variables = new ArrayList<>();

    private static final String mainNode = "int";
    private static final String assignSign = "=";
    private static final String plusSign = "+";
    private static final String divSign = "/";
    private static final String ifStatement = "if";
    private static final String elseStatement = "else";
    private static final String whileStatement = "while";
    private static final String greaterSign = ">";
    private static final String andSign = "&&";

    private boolean checkInstance() {
        return stack.peek() instanceof Symbol || stack.peek() instanceof VarNode
            || stack.peek() instanceof IntNode || stack.peek() instanceof PlusNode
            || stack.peek() instanceof DivNode || stack.peek() instanceof BracketNode;
    }

    public Expression build() {
        Expression expr = null;
        while (checkInstance()) {
            if (stack.peek() instanceof Symbol) {
                Symbol symbol = (Symbol) stack.peek();
					if (symbol.getSymbol().compareTo(mainNode) == 0)
						return stack.peek();
					else if (symbol.getSymbol().compareTo(assignSign) == 0) {
						stack.pop();
						expr = new AssignmentNode(stack.peek(), expr);
						stack.pop();
						if (stack.peek() instanceof SequenceNode || stack.peek() instanceof BlockNode)
							return expr;
						else if (stack.peek() instanceof MainNode) {
							return new SequenceNode(expr);
						} else {
							stack.push(new SequenceNode(stack.pop()));
							return expr;
						}	
					}
					else if (symbol.getSymbol().compareTo(plusSign) == 0) {
						stack.pop();
						if(expr instanceof PlusNode) {
							PlusNode node = (PlusNode) expr;
                            PlusNode temp = new PlusNode(node.getFirstChild());
                            node.setFirstChild(temp);
                            expr = node;
						} else {
							expr = new PlusNode(expr);
						}
					}
                }
                else if (expr instanceof PlusNode) {
					PlusNode node = (PlusNode) expr;
                    node.setFirstChild(stack.pop());
					expr = node;
                }
                else
                  	expr = stack.pop();
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
div = "/"
and = "&&"
greater = ">"
assign = "="
statement = "if" | "while"
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

{statement} {stack.push(new Symbol(yytext()));}

{var} {
    if (stack.peek() instanceof Symbol) {
            Symbol symbol_stack = (Symbol) stack.peek();
            if (symbol_stack.getSymbol().compareTo(mainNode) == 0) {
                variables.add(new VarNode(yytext()));
            } else if (symbol_stack.getSymbol().compareTo(divSign) == 0) {
                stack.pop();
                stack.push(new DivNode(stack.pop(), new VarNode(yytext())));
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
            stack.push(new DivNode(stack.pop(), new IntNode(yytext())));
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

{open_parenthesis} {stack.push(new BracketNode());}

{close_parenthesis} {
    Expression expression = null, anotherExpression = null;
    while (!(stack.peek() instanceof BracketNode)) {
        if (stack.peek() instanceof Symbol) {

            Symbol symbol = (Symbol) stack.peek();

            if (symbol.getSymbol().compareTo(plusSign) == 0) {
                stack.pop();
                if (expression instanceof PlusNode) {
                    PlusNode node = (PlusNode) expression;
                    PlusNode temp = new PlusNode (node.getFirstChild());
                    node.setFirstChild(temp);
                    expression = node;

                } else if (expression instanceof GreaterNode) {

                    GreaterNode node = (GreaterNode) expression;

                    if (node.getFirstChild() instanceof PlusNode) {

                        PlusNode temp = (PlusNode) node.getFirstChild();
                        PlusNode aux = new PlusNode(temp.getFirstChild());
                        temp.setFirstChild(aux);
                        node.setFirstChild(temp);
                        expression = node;
                    } else {
                        PlusNode temp = new PlusNode(node.getFirstChild());
                        node.setFirstChild(temp);
                        expression = node;
                    }
                } else {
                    expression = new PlusNode(expression);
                }
            } else if (symbol.getSymbol().compareTo(greaterSign) == 0) {

                stack.pop();
                expression = new GreaterNode(stack.peek(), expression);
                stack.pop();
            } else if (symbol.getSymbol().compareTo(andSign) == 0) {

                stack.pop();
                if (anotherExpression instanceof AndNode) {

                    AndNode node = (AndNode) anotherExpression;
                    node.setFirstChild(new AndNode(expression));
                    anotherExpression = node;
                    expression = null;
                } else {
                    anotherExpression = new AndNode(expression);
                    expression = null;
                }
            }
        } else if (expression instanceof PlusNode) {

            PlusNode node = (PlusNode) expression;
            node.setFirstChild(stack.peek());
            stack.pop();
            expression = node;
        } else if (expression instanceof GreaterNode) {

            GreaterNode node = (GreaterNode) expression;
            if (node.getFirstChild() instanceof PlusNode) {

                PlusNode temp = (PlusNode) node.getFirstChild();
                temp.setFirstChild(stack.pop());
                node.setFirstChild(temp);
                expression = node;
            }
        } else {
            expression = stack.peek();
            stack.pop();
        }
    }
    if (anotherExpression instanceof AndNode) {

        AndNode node = (AndNode) anotherExpression;
        node.setFirstChild(expression);
        expression = node;
    }
    
    expression = new BracketNode(expression);
    stack.pop();
    
    if (stack.peek() instanceof Symbol) {

        Symbol symbol = (Symbol) stack.peek();

        if (symbol.getSymbol().compareTo(ifStatement) == 0) {

            stack.pop();
            expression = new IfNode(expression);
        
        } else if (symbol.getSymbol().compareTo("!") == 0) {

            expression = new NotNode(expression);
            stack.pop();
        
        } else if (symbol.getSymbol().compareTo(whileStatement) == 0) {

            expression = new WhileNode(expression);
            stack.pop();
        } else if (symbol.getSymbol().compareTo(divSign) == 0) {
            stack.pop();
            expression = new DivNode(stack.peek(), expression);
            stack.pop();
        }
    }
    stack.push(expression);
}

{open_bracket} {stack.push(new BlockNode());}

{close_bracket} {
    Expression expression = stack.peek();
    stack.pop();
    //System.out.println(stack.peek().show());
    if (!(expression instanceof BlockNode)) {
        
        while (!(stack.peek() instanceof BlockNode)) {
            //System.out.println(stack.peek().show());
            if (stack.peek() instanceof SequenceNode) {
                
                SequenceNode node = (SequenceNode) stack.pop();
                node.setSecondStatement(expression);
                expression = node;
                //System.out.println(stack.peek().show());
            }
            expression = new BlockNode(expression);
            stack.pop();
        }
    }
    if (stack.peek() instanceof Symbol) {

        Symbol symbol = (Symbol) stack.peek();

        if (symbol.getSymbol().compareTo(elseStatement) == 0) {

            stack.pop();
            IfNode node = (IfNode) stack.peek();
            stack.pop();
            node.setElseBlock(expression);
            expression = node;
            
            if (stack.peek() instanceof SequenceNode || stack.peek() instanceof BlockNode)
                stack.push(expression);

            else if (stack.peek() instanceof MainNode) {
                System.out.println("muie1");
                stack.push(new SequenceNode(expression));

            } else {
                Expression expr = stack.peek();
                stack.pop();
                System.out.println("muie2");
                stack.push(new SequenceNode(expr));
                stack.push(expression);
            }
        }
    } else if (stack.peek() instanceof IfNode) {

        IfNode node = (IfNode) stack.peek();
        stack.pop();
        node.setThenBlock(expression);
        expression = node;
        stack.push(expression);
    }
}

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
            System.out.println("muie3");
            stack.push(new MainNode (new SequenceNode()));
        }
    }
}

{ignore_expr} {/* do nothing */}
. {}