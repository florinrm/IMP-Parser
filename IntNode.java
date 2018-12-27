import java.*;
import java.util.*;

// IntNode - number
public class IntNode implements Expression {
    String number;
    public IntNode (String number) {
        this.number = number;
    }

    public String show() {
        return "<IntNode> " + number + "\n"; 
    }

    public Expression interpret() {
        return this;
    }
}