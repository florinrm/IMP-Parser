import java.*;
import java.util.*;

public class AssignmentNode implements Expression {
    public Expression var, expression;
    public AssignmentNode (Expression var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    public String show() {
        String str = "<AssignmentNode> =\n";
        str += Parser.addNewLine(var.show() + expression.show());
        return str;
    }

    public Expression interpret() {
        return null;
    }
}