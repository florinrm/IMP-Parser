import java.util.*;

%%

%class Lexer
%line
%{
    Stack<Expression> stack = new Stack<>();
    // list of variables from program, in order to check what of them are initialized
    ArrayList<Symbol> variables = new ArrayList<>();

    public static final String mainNode = "int";

    Expression build() {
        return null;
    }

    Expression get_nth_element_from_stack(int element_number) {
        Stack<Expression> temp_stack = new Stack<>();

        if (element_number > stack.size()) {
            return null;
        }

        for (int j = 0; j < element_number; ++j) {
            temp_stack.push(stack.pop());
        }

        Expression res = temp_stack.peek();

        for (int j = 0; j < element_number; ++j) {
            stack.push(temp_stack.pop());
        }
        return res;
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
variables_declaration = {var}; | {var}, {variables_declaration}
declaration = {type} {variables_declaration}
assign_value = {var}={aVal};
assign_value_bool = {var}={bVal}
assign_value_var = {var}{assign}{var}
ignore_expr = "\n" | ","

%%

{type} {
    stack.push(new Symbol(mainNode));
}
{var} {
    if (stack.peek() instanceof Symbol) {
            Symbol symbol_stack = (Symbol) stack.peek();
            if (symbol_stack.getSymbol.compareTo(mainNode) == 0)
                variables.add(new Symbol(yytext()));
            else
                stack.push(new Symbol(yytext()));
    }
}
{aVal} {stack.push(new IntNode(yytext()));}
{bVal} {stack.push(new BoolNode(yytext()));}
{assig} {stack.push(new Symbol(yytext()));}
{assign_value} { 
    String val = yytext();
    String[] value = val.split("=");
    VarNode variable = new VarNode(value[0]);
    IntNode integer = new IntNode(value[1]);
    AssignmentNode assign_node = new AssignmentNode(variable, integer);
    if (stack.peek() instanceof SequenceNode) {
        stack.pop();
        stack.push(new SequenceNode(assign_node, null));
    } else if (get_nth_element_from_stack(3) instanceof AssignmentNode) {
        Expression val_node = stack.pop();
        Expression var_node = stack.pop();
        Expression assignment = stack.pop();
        Expression seq = stack.pop();
        stack.push(new SequenceNode(assignment, assign_node));
        stack.push(assignment);
        stack.push(var_node);
        stack.push(val_node);
    }
    stack.push(assign_node);
    stack.push(variable);
    stack.push(integer); 
}
{assign_value_bool} { 
    String val = yytext();
    String[] value = val.split("=");
    VarNode variable = new VarNode(value[0]);
    BoolNode bool = new BoolNode(value[1]);
    AssignmentNode assign_node = new AssignmentNode(variable, bool);
    if (stack.peek() instanceof SequenceNode) {
        stack.pop();
        stack.push(new SequenceNode(assign_node, null));
    } else if (get_nth_element_from_stack(3) instanceof AssignmentNode) {
        Expression val_node = stack.pop();
        Expression var_node = stack.pop();
        Expression assignment = stack.pop();
        Expression seq = stack.pop();
        stack.push(new SequenceNode(assignment, assign_node));
        stack.push(assignment);
        stack.push(var_node);
        stack.push(val_node);
    }
    stack.push(assign_node);
    stack.push(variable);
    stack.push(bool); 
}
{assign_value_var} { 
    String val = yytext();
    String[] value = val.split("=");
    VarNode variable1 = new VarNode(value[0]);
    VarNode variable2 = new VarNode(value[1]);
    AssignmentNode assign_node = new AssignmentNode(variable1, variable2);
    stack.push(assign_node);
    stack.push(variable1);
    stack.push(variable2);
}
{ignore_expr} {// do nothing}