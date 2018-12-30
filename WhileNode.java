import java.*;
import java.util.*;

public class WhileNode implements Expression {
    Expression condition, body;
    public WhileNode (Expression condition, Expression body) {
        this.condition = condition;
        this.body = body;
    }

    public WhileNode (Expression condition) {
        this (condition, null);
    }

    public void setBody (Expression body) {
        this.body = body;
    }

    public String show() {
        String str = "<WhileNode> while\n";
        if (condition != null)
            str += Parser.addNewLine(condition.show());
        if (body != null)
            str += Parser.addNewLine(body.show());
        return str;
    }

    public Expression interpret() {
        if (condition instanceof BracketNode) {
            // conditia cu tot cu paranteza
            BracketNode temp = (BracketNode) condition; /*
            System.out.println("IL FUT PE MIHU IN CUR");
            System.out.println(temp.show());
            System.out.println(temp.getClass()); */
            
            // conditia din paranteza
            Expression aux = temp.getChild();
            //System.out.println(aux.show()); 
            Expression eval = aux.interpret();
            if (eval instanceof BoolNode) {
                BoolNode node = (BoolNode) eval;
                while (node.isTrue()) {
                    Expression muie = body.interpret();
                    eval = aux.interpret();
                    node = (BoolNode) eval;
                }
            }
        }
        return null;
    }
}