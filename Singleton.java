import java.*;
import java.util.*;

public class Singleton {
    private static Singleton value = null;
    public HashMap<String, Expression> var_values = new HashMap<>();
    private Singleton () {
        // MUIE PSD
    }

    public static Singleton getInstance() {
        if (value == null)
            value = new Singleton();
        return value;
    } 
}