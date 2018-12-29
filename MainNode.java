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
                str += entry.getKey() + "=" + entry.getValue().interpret().show().split(" ")[1];
            }
            //str = str.substring(0, str.length() - 1);
            try (FileWriter writer = new FileWriter("output")) {
                writer.write(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return expr;
        }
        return null;
    }
}