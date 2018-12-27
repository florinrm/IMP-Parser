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
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;

        int add = Integer.parseInt(first.number) 
                + Integer.parseInt(second.number);

        IntNode result = new IntNode(add + "");
        return result;
    }
}