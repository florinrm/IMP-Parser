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
		//Lexer l = new Lexer(new FileReader("input"));
		Lexer l = new Lexer(new FileReader(args[0]));
		l.yylex();
		Expression expression = null;
		expression = l.stack.pop();

		for (Map.Entry<String, Expression> entry: Singleton.getInstance().var_values.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().show());
		}

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
		Expression tree_interpretation = ast_tree.interpret();
		// String tree_interpretation = l.stack.pop().interpret();

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("arbore"), "utf-8"))) {
			writer.write(tree);
		}
		/*
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("output"), "utf-8"))) {
			writer.write(tree_interpretation);
		} */

	}
}