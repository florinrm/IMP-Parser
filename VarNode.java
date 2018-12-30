import java.*;
import java.util.*;

// VarNode - string
public class VarNode implements Expression {
    private String string;
    private int value;
    private int line; // for errors or warnings
    public VarNode (String string) {
        this(string, "-1000");
    }

    public VarNode (String string, String val) {
        this.string = string;
        this.value = Integer.parseInt(val);
    }

    public VarNode (String string, int line) {
        this(string, "-1000", line);
    }

    public VarNode (String string, String val, int line) {
        this.string = string;
        this.value = Integer.parseInt(val);
        this.line = line;
    }

    public String getVarName() {
        return string;
    }

    public void setVarName (String string) {
        this.string = string;
    }

    public String show() {
        //return line + " <VariableNode> " + string + "\n"; 
        if (!Singleton.getInstance().declared_variables.contains(string)) {
            Singleton.getInstance().undeclared_var = true;            
        }
        return "<VariableNode> " + string + "\n"; 
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Expression interpret() {
        return Singleton.getInstance().var_values.get(string);
    }
}