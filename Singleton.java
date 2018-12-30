import java.*;
import java.util.*;
import java.io.*;

public class Singleton {
    private static Singleton value = null;
    public HashMap<String, Expression> var_values = new HashMap<>();
    public HashSet<String> declared_variables = new HashSet<>();
    public static int count = 1;

    public boolean undeclared_var = false;
    public int undeclared_line = 0;
    public String error_message = "";

    public Expression tree;
    private Singleton () {
        // MUIE PSD
        // MUIE DRAGNEA
    }

    public static Singleton getInstance() {
        if (value == null)
            value = new Singleton();
        return value;
    } 

    public void finishProgram (String message, int line) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output");
        } catch (Exception e) {
            e.printStackTrace();
        }

        writer.println(message + " " + line);
        writer.close();
        System.exit(0);
    }
}