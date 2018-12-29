import java.*;
import java.util.*;

public class SequenceNode implements Expression {
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
        String str =  "<SequenceNode>\n";
        if (firstStatement == null && secondStatement != null)
            str += Parser.addNewLine(secondStatement.show());
        if (firstStatement == null && secondStatement == null)
            return str; 
        if (secondStatement != null)
            str += Parser.addNewLine(firstStatement.show() + secondStatement.show());
        else
            str += Parser.addNewLine(firstStatement.show());
        return str;
    }

    public Expression interpret() {
        if (firstStatement != null) {
            firstStatement.interpret();
        }
        if (secondStatement != null) {
            secondStatement.interpret();
        }
        return null;
    }
}