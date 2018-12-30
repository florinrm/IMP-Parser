import java.*;
import java.util.*;

// IntNode - number
public class IntNode implements Expression {
    String number;
    int line;
    public IntNode (String number) {
        this.number = number;
    }

    public IntNode (String number, int line) {
        this.number = number;
        this.line = line;
    }

    public String show() {
        //return line + " <IntNode> " + number + "\n"; 
        return "<IntNode> " + number + "\n"; 
    }

    public Expression interpret() {
        return this;
    }
}