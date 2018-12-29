import java.*;
import java.util.*;

public class WhileNode implements Expression {
    Expression condition, body;
    public WhileNode (Expression condition, Expression body) {
        this.condition = condition;
        this.body = body;
    }

    public WhileNode (Expression condition) {
        this (condition, null);
    }

    public void setBody (Expression body) {
        this.body = body;
    }

    public String show() {
        String str = "<WhileNode> while\n";
        if (condition != null)
            str += Parser.addNewLine(condition.show());
        if (body != null)
            str += Parser.addNewLine(body.show());
        return str;
    }

    public Expression interpret() {
        return null;
    }
}