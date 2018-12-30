import java.*;
import java.util.*;

public class BracketNode implements Expression {
    private Expression child;
    private int line; // for errors or warnings

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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Expression interpret() {
        if (child != null)
            return child.interpret();
        return null;
    }
}