import java.*;
import java.util.*;

public class AndNode implements Expression {
    private Expression first_child, second_child;
    public AndNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public AndNode (Expression child) {
        this(null, child);
    }

    public Expression getFirstChild() {
        if (this.first_child instanceof AndNode) {
            AndNode node = (AndNode) this.first_child;
            return node.getFirstChild();
        } else {
            return this.first_child;
        }
    }

    public Expression getSecondChild() {
        return second_child;
    }

    public void setFirstChild (Expression first_child) {
        if (this.first_child instanceof AndNode) {
            AndNode node = (AndNode) this.first_child;
            node.setFirstChild(first_child);
            this.first_child = node;
        } else {
            this.first_child = first_child;
        }
    }

    public void setSecondChild (Expression second_child) {
        this.second_child = second_child;
    }

    public String show() {
        String str = "<AndNode> &&\n";
        if (second_child == null && first_child == null)
            return str;
        if (first_child != null && second_child == null)
            str += Parser.addNewLine(first_child.show());
        else if (first_child == null && second_child != null)
            str += Parser.addNewLine(second_child.show());
        else
            str += Parser.addNewLine(first_child.show() + second_child.show());
        return str;
    }    

    public Expression interpret() {
        Expression first = first_child.interpret();
        Expression second = second_child.interpret();
        if (first instanceof BoolNode && second instanceof BoolNode) {
            BoolNode first_bool = (BoolNode) first;
            BoolNode second_bool = (BoolNode) second;
            if (first_bool.isTrue() && second_bool.isTrue())
                return new BoolNode(BoolNode.trueValue);
            else
                return new BoolNode(BoolNode.falseValue);
        }
        return null;
    }
}