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

		l.yylex();
		Expression expression = null;
		
		System.out.println(l.stack.size());
		expression = l.stack.pop();
		//System.out.println(expression.show());
		
		//System.out.println(l.stack.size()); 
		/*
		while (!l.stack.empty()) {
			System.out.println(l.stack.peek().getClass());
			System.out.println(l.stack.peek().show());
			l.stack.pop();
		}*/
		
		Stack<Expression> st = new Stack<Expression>();
		st.addAll(l.stack); 
		System.out.println("Stack size" + st.size());
		while (!st.empty()) {
			System.out.println(st.peek().show());
			//System.out.println(st.peek().getClass());
			st.pop();
		}
		//System.out.println(expression.show());
		while (!(l.stack.peek() instanceof MainNode)) {
			if (l.stack.peek() instanceof SequenceNode) {
				SequenceNode seq = (SequenceNode) l.stack.pop();
				seq.setSecondStatement(expression);
				//System.out.println(expression.show());
				expression = seq;
			}
		} 
		l.stack.pop();
		l.stack.push(new MainNode(expression));
		String tree = l.stack.pop().show();
		System.out.println(tree);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("arbore"), "utf-8"))) {
			writer.write(tree);
		}
		//System.out.println(((ExprList) l.stack.pop()).interpret().show());

	}
}