import java.*;
import java.util.*;

public class GreaterNode implements Expression {
    private Expression first_child, second_child;
    public GreaterNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public Expression getFirstChild() {
        return first_child;
    }

    public Expression getSecondChild() {
        return second_child;
    }

    public void setFirstChild(Expression first_child) {
        this.first_child = first_child;
    }

    public void setSecondChild (Expression second_child) {
        this.second_child = second_child;
    }

    public String show() {
        String str = "<GreaterNode> >\n";
        str += Parser.addNewLine(first_child.show() + second_child.show());
        return str;
    }   
    
    public Expression interpret() {
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;
        if (first.number.compareTo(second.number) > 0)
            return new BoolNode("True");
        return new BoolNode("False");
    }
}