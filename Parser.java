import java.io.*;
import java.util.*;

public class Parser {
	public static String addNewLine(String print) {
		Scanner scanner = new Scanner(print);
		String build = "";
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			build += "\t" + line + "\n";
		}
		scanner.close();
		return build;
	}
	
	public static void main (String[] args) throws IOException {
		Lexer l = new Lexer(new FileReader("input"));
		//Lexer l = new Lexer(new FileReader(args[0]));
		l.toIntepret = false;
		l.yylex();
		Expression expression = null;
		expression = l.stack.pop();

		while (!(l.stack.peek() instanceof MainNode)) {
			if (l.stack.peek() instanceof SequenceNode) {
				SequenceNode seq = (SequenceNode) l.stack.pop();
				seq.setSecondStatement(expression);
				expression = seq;
			}
		} 
		l.stack.pop();
		l.stack.push(new MainNode(expression));
		Expression ast_tree = l.stack.pop();
		String tree = ast_tree.show();
		Singleton.getInstance().tree = ast_tree;

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("arbore"), "utf-8"))) {
			//System.out.println(tree);
			writer.write(tree);
			writer.close();
		}

		if (Singleton.getInstance().undeclared_var) {
			Singleton.getInstance().finishProgram(Singleton.getInstance().error_message, Singleton.getInstance().undeclared_line);
		}

		Lexer lex = new Lexer(new FileReader("input"));
		
		//Lexer lex = new Lexer(new FileReader(args[0]));
		lex.toIntepret = true;
		Singleton.count = 1;
		lex.yylex(); /*
		expression = lex.stack.pop();
		while (!(lex.stack.peek() instanceof MainNode)) {
			if (lex.stack.peek() instanceof SequenceNode) {
				SequenceNode seq = (SequenceNode) lex.stack.pop();
				seq.setSecondStatement(expression);
				expression = seq;
			}
		} 
		lex.stack.pop();
		lex.stack.push(new MainNode(expression));
		ast_tree = lex.stack.pop();
		Singleton.getInstance().tree = ast_tree; */
		ast_tree.interpret();
	}
}