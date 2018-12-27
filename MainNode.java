import java.*;
import java.util.*;

public class MainNode implements Expression {
    Expression child;
    public MainNode (Expression child) {
        this.child = child;
    }

    public String show() {
        String str = "<MainNode>\n";
        if (child != null)
            str += Parser.addNewLine(child.show());
        return str;
    }

    public Expression interpret() {
        return null;
    }
}