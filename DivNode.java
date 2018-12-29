import java.*;
import java.util.*;

class DivNode implements Expression {
    Expression first_child, second_child;
    public DivNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public String show() {
        String str = "<DivNode> /\n";
        if (second_child != null)
            str += Parser.addNewLine(first_child.show() + second_child.show());
        else
            str += Parser.addNewLine(first_child.show());
        return str;
    }

    public Expression interpret() {
        Expression first = first_child;
        Expression second = second_child;

        int result = 0, one = 0, two = 0;

        if (first instanceof VarNode) {
            VarNode node = (VarNode) first;
            Expression temp = Singleton.getInstance().var_values.get(node.getVarName());
            IntNode aux = (IntNode) temp;
            one = Integer.parseInt(aux.number);
        } else if (first instanceof IntNode) {
            IntNode aux = (IntNode) first;
            one = Integer.parseInt(aux.number);
        } else if (first instanceof PlusNode) {
            PlusNode node = (PlusNode) first;
            first = node.interpret();
            if (first instanceof IntNode) {
                IntNode temp = (IntNode) first;
                one = Integer.parseInt(temp.number);
            }
        } else if (first instanceof DivNode) {
            DivNode node = (DivNode) first;
            first = node.interpret();
            if (first instanceof IntNode) {
                IntNode temp = (IntNode) first;
                one = Integer.parseInt(temp.number);
            }
        }

        if (second instanceof VarNode) {
            VarNode node = (VarNode) second;
            Expression temp = Singleton.getInstance().var_values.get(node.getVarName());
            IntNode aux = (IntNode) temp;
            two = Integer.parseInt(aux.number);
        } else if (second instanceof IntNode) {
            IntNode aux = (IntNode) second;
            two = Integer.parseInt(aux.number);
        } else if (second instanceof PlusNode) {
            PlusNode node = (PlusNode) second;
            second = node.interpret();
            if (second instanceof IntNode) {
                IntNode temp = (IntNode) second;
                two = Integer.parseInt(temp.number);
            }
        } else if (second instanceof DivNode) {
            DivNode node = (DivNode) second;
            second = node.interpret();
            if (second instanceof IntNode) {
                IntNode temp = (IntNode) second;
                two = Integer.parseInt(temp.number);
            }
        }

        IntNode res = null;

        try {
            result = one / two;
            res = new IntNode(result + "");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        return res;
    }
}