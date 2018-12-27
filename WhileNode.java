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

    public String show() {
        return "<WhileNode> while\n";
    }

    public Expression interpret() {
        return null;
    }
}