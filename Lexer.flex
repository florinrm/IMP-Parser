
import java.util.*;

%%

%class Lexer
%line
%int
%{
    Stack<Expression> stack = new Stack<>();
    // list of variables from program, in order to check what of them are initialized
    ArrayList<VarNode> variables = new ArrayList<>();
	public static HashMap<String, Expression> var_values = new HashMap<>();

    private static final String mainNode = "int";
    private static final String assignSign = "=";
    private static final String plusSign = "+";
    private static final String divSign = "/";
    private static final String ifStatement = "if";
    private static final String elseStatement = "else";
    private static final String whileStatement = "while";
    private static final String greaterSign = ">";
    private static final String andSign = "&&";
    private static final String notSign = "!";

	public boolean toIntepret = false;


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

						VarNode muie_dragnea = (VarNode) stack.peek();
						muie_dragnea.setLine(Singleton.count);
						if (expr instanceof VarNode) {
							VarNode muie_viorica = (VarNode) expr;
							muie_viorica.setLine(Singleton.count);
							expr = new AssignmentNode(muie_dragnea, muie_viorica, Singleton.count);
						} else {
							expr = new AssignmentNode(muie_dragnea, expr, Singleton.count);
						}
						
						//System.out.println("Muia\n" + stack.peek().show() + "\n" + expr.show());
						//System.out.println("Pula\n" + stack.peek().getClass() + "\n" + expr.getClass());
						
						AssignmentNode node = (AssignmentNode) expr;
						Expression variable = node.getVariable();
						Expression value = node.getValue();
						if (toIntepret) {
							if (variable instanceof VarNode) {
								VarNode temp = (VarNode) variable;
								System.out.println("Var name " + temp.getVarName());
								if (value instanceof VarNode) {
									VarNode aux = (VarNode) value;
									Singleton.getInstance().var_values.put(temp.getVarName(), Singleton.getInstance().var_values.get(aux.getVarName()));
								} else if (value instanceof IntNode || value instanceof BoolNode) {
									Singleton.getInstance().var_values.put(temp.getVarName(), value.interpret());
								} else if (value instanceof PlusNode || value instanceof DivNode) {
									//Singleton.getInstance().var_values.put(temp.getVarName(), value);
								}
							}
						}
						
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
                            PlusNode temp = new PlusNode(node.getFirstChild(), Singleton.count);
                            node.setFirstChild(temp);
                            expr = node;
						} else {
							expr = new PlusNode(expr, Singleton.count);
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
var = [a-z]+
aVal = [1-9][0-9]* | 0
bVal = "true" | "false"
add = "+"
div = "/"
and = "&&"
greater = ">"
assign = "="
statement = "if" | "while" | "else"
type = "int"
close_instr = ";"
new_line = "\n"
ignore_expr = ","
open_bracket = "{"
close_bracket = "}"
open_parenthesis = "("
close_parenthesis = ")"
not = "!"

%%

{type} {
    stack.push(new Symbol(mainNode));
}

{statement} {
    stack.push(new Symbol(yytext()));
}

{bVal} { 
	stack.push(new BoolNode(yytext()));
}

{var} {
    if (stack.peek() instanceof Symbol) {
            Symbol symbol_stack = (Symbol) stack.peek();
            if (symbol_stack.getSymbol().compareTo(mainNode) == 0) {
				VarNode node = new VarNode(yytext());
				//System.out.println("declare " + node.getVarName());
				if (toIntepret)
					Singleton.getInstance().var_values.put(node.getVarName(), null);
				else
					Singleton.getInstance().declared_variables.add(node.getVarName());
            } else if (symbol_stack.getSymbol().compareTo(divSign) != 0) {
				stack.push(new VarNode(yytext()));
            } else {
                stack.pop();
                stack.push(new DivNode(stack.pop(), new VarNode(yytext(), Singleton.count), Singleton.count));
            }
    } else {
        stack.push(new VarNode(yytext()));
    }
}

{aVal} {
    if (stack.peek() instanceof Symbol) {
        Symbol symbol = (Symbol) stack.peek();
        if (symbol.getSymbol().compareTo(divSign) != 0) {
			stack.push(new IntNode(yytext(), Singleton.count));
        } else {
            stack.pop();
            stack.push(new DivNode(stack.pop(), new IntNode(yytext(), Singleton.count), Singleton.count));
        }
    } else {
        stack.push(new IntNode(yytext(), Singleton.count));
    }    
}

{not} {
    stack.push(new Symbol(yytext()));
}

{assign} {
    stack.push(new Symbol(yytext()));
}

{add} {
    stack.push(new Symbol(yytext()));
}

{div} {
    stack.push(new Symbol(yytext()));
}

{and} {
    stack.push(new Symbol(yytext()));
}

{greater} {
    stack.push(new Symbol(yytext()));
}

{open_parenthesis} {
    stack.push(new BracketNode());
}

{close_parenthesis} {
    Expression expression = null, anotherExpression = null;
		while(!(stack.peek() instanceof BracketNode)) {
			if (stack.peek() instanceof Symbol) {
                Symbol symbol = (Symbol) stack.peek();
				if (symbol.getSymbol().compareTo(plusSign) == 0) {
					stack.pop();
					if (expression instanceof PlusNode) {
						PlusNode node = (PlusNode) expression;
						PlusNode temp = new PlusNode (node.getFirstChild(), Singleton.count);
						node.setFirstChild(temp);
						expression = node;
					}
					else if (expression instanceof GreaterNode) {
							GreaterNode node = (GreaterNode) expression;
							if(node.getFirstChild() instanceof PlusNode) {
								PlusNode temp = (PlusNode) node.getFirstChild();
								PlusNode aux = new PlusNode(temp.getFirstChild(), Singleton.count);
								temp.setFirstChild(aux);
								node.setFirstChild(temp);
								expression = node;
							} else {
								PlusNode temp = new PlusNode(node.getFirstChild(), Singleton.count);
								node.setFirstChild(temp);
								expression = node;
							}
					} else {
						expression = new PlusNode(expression, Singleton.count);
					}
				} else if (symbol.getSymbol().compareTo(greaterSign) == 0) {
					stack.pop();
					if (stack.peek() instanceof VarNode && expression instanceof VarNode) {
						VarNode first = (VarNode) stack.peek();
						VarNode second = (VarNode) expression;
						first.setLine(Singleton.count);
						second.setLine(Singleton.count);
						expression = new GreaterNode(first, second);
					} else if (stack.peek() instanceof VarNode) {
						VarNode first = (VarNode) stack.peek();
						first.setLine(Singleton.count);
						expression = new GreaterNode(first, expression);
					} else if (expression instanceof VarNode) {
						VarNode second = (VarNode) expression;
						second.setLine(Singleton.count);
						expression = new GreaterNode(stack.peek(), second);
					} else {
						expression = new GreaterNode(stack.peek(), expression);
					}
					stack.pop();
				} 
				else if (symbol.getSymbol().compareTo(andSign) == 0) {
					stack.pop();
					if (anotherExpression instanceof AndNode) {
						AndNode node = (AndNode) anotherExpression;
						node.setFirstChild(new AndNode(expression));
						anotherExpression = node;
						expression = null;
					}
					else {
						anotherExpression = new AndNode(expression);
						expression = null;
					}
				}
			}
			else if (expression instanceof PlusNode) {
				PlusNode node = (PlusNode) expression;
            	node.setFirstChild(stack.peek());
            	stack.pop();
            	expression = node;
            }
			else if (expression instanceof GreaterNode) {
				GreaterNode node = (GreaterNode) expression;
				if (node.getFirstChild() instanceof PlusNode) {
					PlusNode temp = (PlusNode) node.getFirstChild();
					temp.setFirstChild(stack.pop());
					node.setFirstChild(temp);
					expression = node;
                }
			}
			else {
				expression = stack.peek();
				stack.pop();
            }
		}
		stack.pop();
		if (anotherExpression instanceof AndNode) {
			AndNode node = (AndNode) anotherExpression;
			node.setFirstChild(expression);
			expression = node;
		}	
		expression = new BracketNode(expression);
		if (stack.peek() instanceof Symbol) {
			Symbol symbol = (Symbol) stack.peek();
			if (symbol.getSymbol().compareTo(ifStatement) == 0) {
				stack.pop();
				expression = new IfNode(expression);	
			} 
			else if (symbol.getSymbol().compareTo(notSign) == 0) {
				stack.pop();
				expression = new NotNode(expression);	
			} 
			else if (symbol.getSymbol().compareTo(whileStatement) == 0)  {
				stack.pop();				
				expression = new WhileNode(expression);	
			} 
			else if (symbol.getSymbol().compareTo(divSign) == 0)  {
				stack.pop();
				expression = new DivNode(stack.peek(), expression, Singleton.count);
				stack.pop();
			} 
		}
		stack.push(expression);
}

{open_bracket} {
    stack.push(new BlockNode());
}

{close_bracket} {
    Expression expression = stack.pop();
	if (!(expression instanceof BlockNode)) {
		while (!(stack.peek() instanceof BlockNode)) 
			if(stack.peek() instanceof SequenceNode) {
            	SequenceNode node = (SequenceNode) stack.pop();
                node.setSecondStatement(expression);
                expression = node;
            }
		expression = new BlockNode(expression);
		stack.pop();
	}
	if (stack.peek() instanceof IfNode) {
        IfNode node = (IfNode) stack.pop();
        node.setThenBlock(expression);
        expression = node;
		stack.push(expression);
	} else if (stack.peek() instanceof Symbol)  {
		Symbol symbol = (Symbol) stack.peek();	
			if(symbol.getSymbol().compareTo(elseStatement) == 0) {
				stack.pop();
                IfNode node = (IfNode) stack.pop();
                node.setElseBlock(expression);
                expression = node;
				if(stack.peek() instanceof SequenceNode || stack.peek() instanceof BlockNode) {
					stack.push(expression);	
				} else if (stack.peek() instanceof MainNode) {
					stack.push(new SequenceNode(expression));
				} else { 
					stack.push(new SequenceNode(stack.pop()));
					stack.push(expression);
				}
			}
	} else if (stack.peek() instanceof WhileNode) {
            WhileNode node = (WhileNode) stack.pop();
            node.setBody(expression);
            expression = node;
			if (stack.peek() instanceof SequenceNode || stack.peek() instanceof BlockNode) {
				stack.push(expression);	
			} else if (stack.peek() instanceof MainNode) {
				stack.push(new SequenceNode(expression));
			} else { 
				stack.push(new SequenceNode(stack.pop()));
				stack.push(expression);
			}
		}
}

{close_instr} {
    Expression expr = build();
    if (expr instanceof Symbol) {
		Symbol symbol = (Symbol) expr;
        if (symbol.getSymbol().compareTo(mainNode) != 0) {
            stack.push(expr);
        } else {
            stack.pop();
            stack.push(new MainNode (new SequenceNode()));
        }
    } else {
        stack.push(expr);
    }
}

{ignore_expr} {/* do nothing */}
{new_line} {Singleton.count++;}
. {}