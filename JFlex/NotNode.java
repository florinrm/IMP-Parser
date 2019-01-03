import java.*;
import java.util.*;

public class NotNode implements Expression {
    Expression child;
    public NotNode (Expression child) {
        this.child = child;
    }

    public String show() {
        String str = "<NotNode> !\n";
        str += Parser.addNewLine(child.show());
        return str;
    }    

    public Expression interpret() {
        Expression node = child.interpret();
        if (node instanceof BoolNode) {
            BoolNode bool = (BoolNode) node;
            if (bool.isTrue())
                return new BoolNode(BoolNode.falseValue);
            else
                return new BoolNode(BoolNode.trueValue);
        }
        return null;
    }
}