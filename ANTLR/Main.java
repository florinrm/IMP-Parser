// Multumim echipei de CPL pentru acest schelet Main

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HelloLexer lexer = null;
        CommonTokenStream tokenStream = null;
        HelloParser parser = null;
        ParserRuleContext globalTree = null;

        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;

        // Deschidem fisierul input pentru a incepe parsarea
        String fileName = "input";
        CharStream input = CharStreams.fromFileName(fileName);

        // Definim Lexer-ul
        lexer = new HelloLexer(input);

        // Obtinem tokenii din input
        tokenStream = new CommonTokenStream(lexer);

        // Definim Parser-ul
        parser = new HelloParser(tokenStream);

        // Incepem parsarea
        ParserRuleContext tree = parser.main();

        // Vizitam AST-ul
        MyVisitor visitor = new MyVisitor();
        visitor.visit(tree);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("arbore"), "utf-8"))) {
			//System.out.println(tree);
			writer.write(visitor.res);
			writer.close();
		}
    }
}
