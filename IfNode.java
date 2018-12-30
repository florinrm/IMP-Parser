import java.*;
import java.util.*;


public class IfNode implements Expression {
    private Expression condition, thenBlock, elseBlock;
    public IfNode (Expression condition, Expression thenBlock, Expression elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public IfNode (Expression condition) {
        this(condition, null, null);
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getThenBlock() {
        return thenBlock;
    }

    public Expression getElseBlock() {
        return elseBlock;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public void setThenBlock (Expression thenBlock) {
        this.thenBlock = thenBlock;
    }

    public void setElseBlock (Expression elseBlock) {
        this.elseBlock = elseBlock;
    }

    public String show() {
        String str = "<IfNode> if\n";
        if (elseBlock != null)
            str += Parser.addNewLine(condition.show() + thenBlock.show() + elseBlock.show());
        else
            str += Parser.addNewLine(condition.show() + thenBlock.show());
        return str;
    }

    public Expression interpret() {
        Expression result = condition.interpret();
        if (result instanceof BoolNode) {
            BoolNode bool = (BoolNode) result;
            if (bool.isTrue())
                return thenBlock.interpret();
            else
                return elseBlock.interpret();
        }
        return null;
    }
}