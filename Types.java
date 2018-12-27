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
        return "<IntNode> " + number + "\n"; 
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
        return "<VariableNode> " + string + "\n"; 
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
        return "<BoolNode> " + value + "\n";
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
        String str = "<MainNode>\n";
        if (child != null)
            str += Parser.addNewLine(child.show());
        return str;
    }

    public Expression interpret() {
        return null;
    }
}

class PlusNode implements Expression {
    private Expression first_child, second_child;
    public PlusNode (Expression first_child, Expression second_child) {
        this.first_child = first_child;
        this.second_child = second_child;
    }

    public PlusNode (Expression child) {
        this(null, child);
    }

    public void setFirstChild (Expression first_child) {
        if (this.first_child instanceof PlusNode) {
            PlusNode node = (PlusNode) this.first_child;
            node.setFirstChild(first_child);
            this.first_child = node;
            System.out.println("muie");
        } else
            this.first_child = first_child;
    }

    public void setSecondChild (Expression second_child) {
        this.second_child = second_child;
    }

    public Expression getFirstChild () {
        if (this.first_child instanceof PlusNode) {
            PlusNode node = (PlusNode) this.first_child;
            return node.getFirstChild();
        } else
            return this.first_child;
    }

    public Expression getSecondChild() {
        return second_child;
    }

    public String show() {
        String str = "<PlusNode> +\n";
        if (second_child != null)
            str += Parser.addNewLine(first_child.show() + second_child.show());
        else
            str += Parser.addNewLine(first_child.show());
        return str;
    }

    public Expression interpret() {
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;

        int add = Integer.parseInt(first.number) 
                + Integer.parseInt(second.number);

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
        String str = "<DivNode> /\n";
        if (second_child != null)
            str += Parser.addNewLine(first_child.show() + second_child.show());
        else
            str += Parser.addNewLine(first_child.show());
        return str;
    }

    public Expression interpret() {
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;

        IntNode result = null;

        try {
            int div = Integer.parseInt(first.number) 
                    / Integer.parseInt(second.number);
            result = new IntNode(div + "");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        return result;
    }
}

class BracketNode implements Expression {
    private Expression child;

    public BracketNode (Expression child) {
        this.child = child;
    }

    public BracketNode() {
        this(null);
    }

    public void setChild(Expression child) {
        this.child = child;
    }

    public Expression getChild() {
        return this.child;
    }

    public String show() {
        String str = "<BracketNode> ()\n";
        str += Parser.addNewLine(child.show());
        return str;
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
        String str = "<AndNode> &&\n";
        str += Parser.addNewLine(first_child.show() + second_child.show());
        return str;
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
        String str = "<GreaterNode> >\n";
        str += Parser.addNewLine(first_child.show() + second_child.show());
        return str;
    }   
    
    public Expression interpret() {
        IntNode first = (IntNode) first_child;
        IntNode second = (IntNode) second_child;
        if (first.number.compareTo(second.number) > 0)
            return new BoolNode("True");
        return new BoolNode("False");
    }
}

class NotNode implements Expression {
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
        return null;
    }
}

class AssignmentNode implements Expression {
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

class BlockNode implements Expression {
    Expression statement;
    public BlockNode (Expression statement) {
        this.statement = statement;
    }

    public BlockNode () {
        this(null);
    }

    public String show() {
        return "<BlockNode> {}\n" + statement.show();
    }

    public Expression interpret() {
        return null;
    }
}

class IfNode implements Expression {
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
        str += Parser.addNewLine(condition.show() + thenBlock.show() + elseBlock.show());
        return str;
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

class SequenceNode implements Expression {
    private Expression firstStatement, secondStatement;
    public SequenceNode (Expression firstStatement, Expression secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public SequenceNode (Expression firstStatement) {
        this(firstStatement, null);
    }

    public SequenceNode() {
        this(null);
    }

    public Expression getFirstStatement() {
        return firstStatement;
    }

    public Expression getSecondStatement() {
        return secondStatement;
    }

    public void setFirstStatement(Expression statement) {
        this.firstStatement = statement;
    }

    public void setSecondStatement(Expression statement) {
        this.secondStatement = statement;
    }

    public String show() {
        String str =  "<SequenceNode>\n"; /*
        if (firstStatement == null)
            System.out.println("NULL FIRST");
        else
            System.out.println("GOOD FIRST");
        if (secondStatement == null)
            System.out.println("NULL SECOND");
        else
            System.out.println("GOOD SECOND"); */
        //str += "\t" + firstStatement.show(); 
        if (secondStatement != null)
            str += Parser.addNewLine(firstStatement.show() + secondStatement.show());
        else
            str += Parser.addNewLine(firstStatement.show());
        return str;
    }

    public Expression interpret() {
        return null;
    }
}

class Symbol implements Expression {
    private String symbol;
    
    public Symbol (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }

    public String show() {
        return symbol + "\n";
    }

    public Expression interpret() {
        return null;
    }
}