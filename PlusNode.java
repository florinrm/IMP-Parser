import java.*;
import java.util.*;

public class PlusNode implements Expression {
    private Expression first_child, second_child;
    public PlusNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public PlusNode (Expression child) {
        this(null, child);
    }

    public void setFirstChild (Expression first_child) {
        if (this.first_child instanceof PlusNode) {
            PlusNode node = (PlusNode) this.first_child;
            node.setFirstChild(first_child);
            this.first_child = node;
        } else
            this.first_child = first_child;
    }

    public void setSecondChild (Expression second_child) {
        this.second_child = second_child;
    }

    public Expression getFirstChild () {
        if (this.first_child instanceof PlusNode) {
            PlusNode node = (PlusNode) this.first_child;
            return node.getFirstChild();
        } else
            return this.first_child;
    }

    public Expression getSecondChild() {
        return second_child;
    }

    public String show() {
        String str = "<PlusNode> +\n";
        if (second_child != null)
            str += Parser.addNewLine(first_child.show() + second_child.show());
        else
            str += Parser.addNewLine(first_child.show());
        return str;
    }

    public Expression interpret() {
        Expression first = first_child;
        Expression second = second_child;
        int result = 0, one, two;

        if (first instanceof VarNode) {
            VarNode node = (VarNode) first;
            Expression temp = Singleton.getInstance().var_values.get(node.getVarName());
            IntNode aux = (IntNode) temp;
            one = Integer.parseInt(aux.number);
            result += one;
        } else if (first instanceof IntNode) {
            IntNode aux = (IntNode) first;
            one = Integer.parseInt(aux.number);
            result += one;
        } else if (first instanceof PlusNode) {
            PlusNode node = (PlusNode) first;
            first = node.interpret();
            if (first instanceof IntNode) {
                IntNode temp = (IntNode) first;
                one = Integer.parseInt(temp.number);
                result += one;
            }
        } else if (first instanceof DivNode) {
            DivNode node = (DivNode) first;
            first = node.interpret();
            if (first instanceof IntNode) {
                IntNode temp = (IntNode) first;
                one = Integer.parseInt(temp.number);
                result += one;
            }
        }

        if (second instanceof VarNode) {
            VarNode node = (VarNode) second;
            Expression temp = Singleton.getInstance().var_values.get(node.getVarName());
            IntNode aux = (IntNode) temp;
            two = Integer.parseInt(aux.number);
            result += two;
        } else if (second instanceof IntNode) {
            IntNode aux = (IntNode) second;
            two = Integer.parseInt(aux.number);
            result += two;
        } else if (second instanceof PlusNode) {
            PlusNode node = (PlusNode) second;
            second = node.interpret();
            if (second instanceof IntNode) {
                IntNode temp = (IntNode) second;
                two = Integer.parseInt(temp.number);
                result += two;
            }
        } else if (second instanceof DivNode) {
            DivNode node = (DivNode) second;
            second = node.interpret();
            if (second instanceof IntNode) {
                IntNode temp = (IntNode) second;
                two = Integer.parseInt(temp.number);
                result += two;
            }
        }
        String res = result + "";
        return new IntNode(res);
    }
}