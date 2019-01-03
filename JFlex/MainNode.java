import java.*;
import java.util.*;
import java.io.*;

public class MainNode implements Expression {
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
        if (child != null) {
            Expression expr = child.interpret();
            String str = "";
            for (Map.Entry<String, Expression> entry: Singleton.getInstance().var_values.entrySet()) {
                if (entry.getValue() != null)
                    str += entry.getKey() + "=" + entry.getValue().interpret().show().split(" ")[1];
                else
                    str += entry.getKey() + "=null\n";
            }

            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter ("output");
                printWriter.print(str);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null)
                    printWriter.close();
            }
            return expr;
        }
        return null;
    }
}