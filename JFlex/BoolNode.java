import java.*;
import java.util.*;

public class BoolNode implements Expression {
    private String value;
    private int line; // for errors or warnings

    public final static String trueValue = "true";
    public final static String falseValue = "false";

    public BoolNode (String value) {
        this.value = value;
    }

    public String show() {
        return "<BoolNode> " + value + "\n";
    }

    public Expression interpret() {
        return this;
    }

    public boolean isTrue() {
        return value.equals(trueValue);
    }

    public boolean isFalse() {
        return value.equals(falseValue);
    }

    public String getValue() {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}