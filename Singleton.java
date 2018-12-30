import java.*;
import java.util.*;
import java.io.*;

public class Singleton {
    private static Singleton value = null;
    public HashMap<String, Expression> var_values = new HashMap<>();
    public static int count = 0;
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