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
        Expression first_interpret = first_child.interpret();
        Expression second_interpret = second_child.interpret();

        if (first_interpret instanceof IntNode && second_interpret instanceof IntNode) {
            IntNode first = (IntNode) first_interpret;
            IntNode second = (IntNode) second_interpret;
            int one = Integer.parseInt(first.number);
            int two = Integer.parseInt(second.number);
            if (one > two)
                return new BoolNode(BoolNode.trueValue);
            return new BoolNode(BoolNode.falseValue);
        }
        return null;
    }
}