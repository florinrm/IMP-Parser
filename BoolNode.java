import java.*;
import java.util.*;

public class BoolNode implements Expression {
    String value;
    public BoolNode (String value) {
        this.value = value;
    }

    public String show() {
        return "<BoolNode> " + value + "\n";
    }

    public Expression interpret() {
        return this;
    }
}