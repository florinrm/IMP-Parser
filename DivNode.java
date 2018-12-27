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
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;

        IntNode result = null;

        try {
            int div = Integer.parseInt(first.number) 
                    / Integer.parseInt(second.number);
            result = new IntNode(div + "");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        return result;
    }
}