import java.*;
import java.util.*;

// VarNode - string
public class VarNode implements Expression {
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