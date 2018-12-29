import java.*;
import java.util.*;

// VarNode - string
public class VarNode implements Expression {
    private String string;
    private int value;
    public VarNode (String string) {
        this(string, "-1000");
    }

    public VarNode (String string, String val) {
        this.string = string;
        this.value = Integer.parseInt(val);
    }

    public String getVarName() {
        return string;
    }

    public void setVarName (String string) {
        this.string = string;
    }

    public String show() {
        return "<VariableNode> " + string + "\n"; 
    }

    public Expression interpret() {
        return Singleton.getInstance().var_values.get(string);
    }
}