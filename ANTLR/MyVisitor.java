import java.lang.Integer;

class MyVisitor extends HelloBaseVisitor<Integer> {
    private int tabs = 0;
    public String res = "";

    // Functie rudimentara pentru a printa tab-uri
    private void printTabs() {
        for (int i = 0; i < this.tabs; i++) {
            //System.out.print("\t");
            res += "\t";
        }
    }

    @Override 
    public Integer visitInteger(HelloParser.IntegerContext ctx) {
        //this.printTabs();
        //System.out.println("<IntNode> " + ctx.getText());
        res += "<IntNode> " + ctx.getText() + "\n";
        return 0;
    }

    @Override
    public Integer visitMain (HelloParser.MainContext ctx) {
        this.printTabs();
        //System.out.println("<MainNode>");
        res += "<MainNode>\n";
        this.tabs++;
        visit(ctx.variablesList());
        visit(ctx.sequence());
        visit(ctx.CLOSE_INSTR());

        this.tabs--;
        return 0;
    }

    @Override
    public Integer visitVariablesList (HelloParser.VariablesListContext ctx) {
        return 0;
    }

    @Override
    public Integer visitIf_stmt (HelloParser.If_stmtContext ctx) {
        //System.out.println("<IfNode> if");
        res += "<IfNode> if\n";
        visit(ctx.STAT_IF());
        if (ctx.b_expression() != null)
            visit(ctx.b_expression());
        if (ctx.block(0) != null) {
            visit(ctx.block(0));
        }
        if (ctx.block(1) != null) {
            visit(ctx.block(1)); 
        }
        return 0;
    }

    @Override
    public Integer visitWhile_stmt (HelloParser.While_stmtContext ctx) {
        //System.out.println("<WhileNode> while");
        res += "<WhileNode> while\n";
        visit(ctx.STAT_WHILE());
        if (ctx.b_expression() != null)
            visit(ctx.b_expression());
        if (ctx.block() != null)
            visit(ctx.block());
        return 0;
    }

    @Override
    public Integer visitA_expression (HelloParser.A_expressionContext ctx) {
        printTabs();

        if (ctx.integer() != null) {
            visit(ctx.integer());
        } else if (ctx.variable() != null) {
            visit(ctx.variable());
        } else if (ctx.a_expression(0) != null && ctx.a_expression(1) != null) {
            if (ctx.div() != null) {
                visit(ctx.div());
            }
            if (ctx.plus() != null) {
                visit(ctx.plus());
            }
            this.tabs++;
            visit(ctx.a_expression(0));
            visit(ctx.a_expression(1));
            this.tabs--;
        } else if (ctx.bracket() != null) {
            this.tabs++;
            visit(ctx.bracket());
            this.tabs--;
        }
        return 0;
    }

    @Override
    public Integer visitB_expression (HelloParser.B_expressionContext ctx) {
        printTabs();
        if (ctx.bool() != null) {
            visit(ctx.bool());
        } else if (ctx.not() != null && ctx.b_expression(0) != null) {
            ++this.tabs;
            visit(ctx.not());
            visit(ctx.b_expression(0));
            --this.tabs;
        } else if (ctx.b_expression(0) != null && ctx.b_expression(1) != null && ctx.and() != null) {
            ++this.tabs;
            visit(ctx.and());
            visit(ctx.b_expression(0));
            visit(ctx.b_expression(1));
            --this.tabs;
        } else if (ctx.a_expression(0) != null && ctx.a_expression(1) != null && ctx.greater() != null) {
            ++this.tabs;
            visit(ctx.greater());
            visit(ctx.a_expression(0));
            visit(ctx.a_expression(1));
            --this.tabs;
        } else if (ctx.bracket() != null) {
            ++this.tabs;
            visit(ctx.bracket());
            --this.tabs;
        }
        return 0;
    }

    public Integer visitSequence (HelloParser.SequenceContext ctx) {
        printTabs();
        //System.out.println("<SequenceNode>");
        res += "<SequenceNode>\n";
        this.tabs++;
        if (ctx.statement(0) != null)
            visit(ctx.statement(0));
        if (ctx.sequence() != null)
            visit(ctx.sequence());
        if (ctx.statement(1) != null)
            visit(ctx.statement(1));
        this.tabs--;
        return 0;
    }

    @Override
    public Integer visitStatement (HelloParser.StatementContext ctx) {
        printTabs();
        if (ctx.if_stmt() != null) {
            ++this.tabs;
            visit(ctx.if_stmt());
            --this.tabs;
        } else if (ctx.while_stmt() != null) {
            ++this.tabs;
            visit(ctx.while_stmt());
            --this.tabs;
        } else if (ctx.assign() != null) {
            ++this.tabs;
            //System.out.println("<AssignmentNode> =");
            res += "<AssignmentNode> =\n";
            visit(ctx.assign());
            --this.tabs;
        }
        return 0;
    }

    @Override
    public Integer visitVariable (HelloParser.VariableContext ctx) {
        //--this.tabs;
        //printTabs();
        //System.out.println("<VariableNode> " + ctx.getText());
        res += "<VariableNode> " + ctx.getText() + "\n";
        //++this.tabs;
        return 0;
    }

    @Override
    public Integer visitPlus (HelloParser.PlusContext ctx) {
        //System.out.println("<PlusNode> +");
        res += "<PlusNode> +\n";
        return 0;
    }

    @Override
    public Integer visitAssign (HelloParser.AssignContext ctx) {
        printTabs();
        if (ctx.variable() != null) {
            //this.tabs -= 3;
            visit(ctx.variable());
            //this.tabs += 3;
        }
        if (ctx.a_expression() != null) {
            //--this.tabs;
            visit(ctx.a_expression());
            //++this.tabs;
        }
        //visit(ctx.assign());
        return 0;
    }

    @Override
    public Integer visitDiv (HelloParser.DivContext ctx) {
        //System.out.println("<DivNode> /");
        res += "<DivNode> /\n";
        return 0;
    }

    @Override
    public Integer visitNot (HelloParser.NotContext ctx) {
        //System.out.println("<NotNode> !");
        res += "<NotNode> !\n";
        return 0;
    }

    @Override
    public Integer visitGreater (HelloParser.GreaterContext ctx) {
        //System.out.println("<GreaterNode> >");
        res += "<GreaterNode> >\n";
        return 0;
    }

    @Override
    public Integer visitAnd (HelloParser.AndContext ctx) {
        //System.out.println("<AndNode> &&");
        res += "<AndNode> &&\n";
        return 0;
    }

    public Integer visitBool (HelloParser.BoolContext ctx) {
        //System.out.println("<BoolNode> " + ctx.getText());
        res += "<BoolNode> " + ctx.getText() + "\n";
        return 0;
    }

    public Integer visitBracket (HelloParser.BracketContext ctx) {
        //System.out.println("<BracketNode> ()");
        res += "<BracketNode> ()\n";
        visit(ctx.OPEN_PAR());
        if (ctx.a_expression() != null) {
            visit(ctx.a_expression());
        } else if (ctx.b_expression() != null) {
            visit(ctx.b_expression());
        }
        visit(ctx.CLOSE_PAR());
        return 0;
    }

    public Integer visitBlock (HelloParser.BlockContext ctx) {      
        printTabs();
        ++this.tabs;  
        //System.out.println("<BlockNode> {}");
        res += "<BlockNode> {}\n";
        visit(ctx.OPEN_BRACKET());
        if (ctx.sequence() != null) {
            visit(ctx.sequence());
        } else if (ctx.statement() != null) {
            visit(ctx.statement());
        } 
        --this.tabs;
        visit(ctx.CLOSE_BRACKET());
        return 0;
    }
}
