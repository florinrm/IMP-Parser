import java.*;
import java.util.*;

public class BlockNode implements Expression {
    Expression statement;
    public BlockNode (Expression statement) {
        this.statement = statement;
    }

    public BlockNode () {
        this(null);
    }

    public String show() {
        String str = "<BlockNode> {}\n";
        if (statement != null)
            str += Parser.addNewLine(statement.show());
        return str;
    }

    public Expression interpret() {
        return null;
    }
}