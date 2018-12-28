import java.*;
import java.util.*;

public class BracketNode implements Expression {
    private Expression child;

    public BracketNode (Expression child) {
        this.child = child;
    }

    public BracketNode() {
        this(null);
    }

    public void setChild(Expression child) {
        this.child = child;
    }

    public Expression getChild() {
        return this.child;
    }

    public String show() {
        String str = "<BracketNode> ()\n";
        if (child != null)
            str += Parser.addNewLine(child.show());
        return str;
    }    

    public Expression interpret() {
        return null;
    }
}