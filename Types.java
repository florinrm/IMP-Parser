import java.*;
import java.util.*;

interface Expression {
    String show();
    Expression interpret();
}

// IntNode - number
class IntNode implements Expression {
    String number;
    public IntNode (String number) {
        this.number = number;
    }

    public String show() {
        return "\t<IntNode> " + number; 
    }

    public Expression interpret() {
        return this;
    }
}

// VarNode - string
class VarNode implements Expression {
    String string;
    public VarNode (String string) {
        this.string = string;
    }

    public String show() {
        return "\t<VarNode> " + string; 
    }

    public Expression interpret() {
        return null;
    }
}

class BoolNode implements Expression {
    String value;
    public BoolNode (String value) {
        this.value = value;
    }

    public String show() {
        return "\t<BoolNode> " + value + "\n";
    }

    public Expression interpret() {
        return this;
    }
}

class MainNode implements Expression {
    Expression child;
    public MainNode (Expression child) {
        this.child = child;
    }

    public String show() {
        return "<MainNode>\n";
    }

    public Expression interpret() {
        return null;
    }
}

class PlusNode implements Expression {
    Expression first_child, second_child;
    public PlusNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public String show() {
        return "\t<PlusNode> +\n";
    }

    public Expression interpret() {
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;

        int add = Integer.parseInt(first_child.number) 
                + Integer.parseInt(second_child.number);

        IntNode result = new IntNode(add + "");
        return result;
    }
}

class DivNode implements Expression {
    Expression first_child, second_child;
    public DivNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public String show() {
        return "\t<DivNode> /\n";
    }

    public Expression interpret() {
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;

        IntNode result = null;

        try {
            int div = Integer.parseInt(first_child.number) 
                    / Integer.parseInt(second_child.number);
            result = new IntNode(div + "");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        return result;
    }
}

class BracketNode implements Expression {
    Expression child;
    public BracketNode (Expression child) {
        this.child = child;
    }

    public String show() {
        return "\t<BracketNode> ()\n";
    }    

    public Expression interpret() {
        return null;
    }
}

class AndNode implements Expression {
    Expression first_child, second_child;
    public AndNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public String show() {
        return "\t<AndNode> &&\n";
    }    

    public Expression interpret() {
        return null;
    }
}

class GreaterNode implements Expression {
    Expression first_child, second_child;
    public GreaterNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public String show() {
        return "\t<GreaterNode> >\n";
    }   
    
    public Expression interpret() {
        return null;
    }
}

class NotNode implements Expression {
    Expression child;
    public NotNode (Expression child) {
        this.child = child;
    }

    public String show() {
        return "\t<NotNode> !\n";
    }    

    public Expression interpret() {
        return null;
    }
}

class AssignmentNode implements Expression {
    Expression var, expression;
    public AssignmentNode (Expression var, Expression expression) {
        this.var = var;
        this.expression = expression;
    }

    public String show() {
        return "\t<AssignmentNode> =\n";
    }

    public Expression interpret() {
        return null;
    }
}

class BlockNode implements Expression {
    Expression statement;
    public BlockNode (Expression statement) {
        this.statement = statement;
    }

    public BlockNode () {
        this.statement = null;
    }

    public String show() {
        return "\t<BlockNode> {}\n";
    }

    public Expression interpret() {
        return null;
    }
}

class IfNode implements Expression {
    Expression condition, thenBlock, elseBlock;
    public IfNode (Expression condition, Expression thenBlock, Expression elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public String show() {
        return "\t<IfNode> if\n";
    }

    public Expression interpret() {
        return null;
    }
}

class WhileNode implements Expression {
    Expression condition, body;
    public WhileNode (Expression condition, Expression body) {
        this.condition = condition;
        this.body = body;
    }

    public String show() {
        return "\t<WhileNode> while\n";
    }

    public Expression interpret() {
        return null;
    }
}

class SequenceNode implements Expression {
    Expression firstStatement, secondStatement;
    public WhileNode (Expression firstStatement, Expression secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public String show() {
        return "\t<SequenceNode>\n";
    }

    public Expression interpret() {
        return null;
    }
}