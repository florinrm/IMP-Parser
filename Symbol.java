import java.*;
import java.util.*;

public class Symbol implements Expression {
    private String symbol;
    
    public Symbol (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }

    public String show() {
        return symbol + "\n";
    }

    public Expression interpret() {
        return null;
    }
}