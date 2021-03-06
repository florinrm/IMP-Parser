import java.*;
import java.util.*;

public class AssignmentNode implements Expression {
    private Expression var, expression;
    private int line; // for errors or warnings
    public AssignmentNode (Expression var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    public AssignmentNode (Expression var, Expression expression, int line) {
        this.var = var;
        this.expression = expression;
        this.line = line;
    }

    public String show() {
        String str = "<AssignmentNode> =\n";
        if (expression instanceof PlusNode) {
            PlusNode node = (PlusNode) expression;
            if (node.hasUndeclared()) {
                Singleton.getInstance().undeclared_line = line;
                Singleton.getInstance().error_message = "UnassignedVar";
                Singleton.getInstance().undeclared_var = true;
            }
        } else if (expression instanceof DivNode) {
            DivNode node = (DivNode) expression;
            if (node.hasUndeclared()) {
                Singleton.getInstance().undeclared_line = line;
                Singleton.getInstance().error_message = "UnassignedVar";
                Singleton.getInstance().undeclared_var = true;
            }
        }
        str += Parser.addNewLine(var.show() + expression.show());
        return str;
    }

    public Expression getVariable() {
        return var;
    }

    public Expression getValue() {
        return expression;
    }

    public void setVariable(Expression var) {
        this.var = var;
    }

    public void setValue (Expression expression) {
        this.expression = expression;
    }

    public Expression interpret() {
        if (var instanceof VarNode) {
            VarNode node = (VarNode) var;
            //System.out.println(expression.show());
            Expression temp = expression.interpret();
            //if (temp != null) {
                System.out.println(temp.show());
                System.out.println("costi gay " + node.getVarName());
                if (temp instanceof IntNode) {
                    IntNode aux = (IntNode) temp;
                    Singleton.getInstance().var_values.put(node.getVarName(), aux.interpret());
                } else if (temp instanceof BoolNode) {
                    BoolNode aux = (BoolNode) temp;
                    Singleton.getInstance().var_values.put(node.getVarName(), aux.interpret());
                } else {
                    //System.out.println(temp.getClass());
                    Singleton.getInstance().var_values.put(node.getVarName(), temp.interpret());
                }
            //}
            //Singleton.getInstance().put(node, )
        }
        return var.interpret();
    }
}